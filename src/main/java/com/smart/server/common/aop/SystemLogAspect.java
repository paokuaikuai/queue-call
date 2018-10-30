package com.smart.server.common.aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import com.smart.client.SessionUser;
import com.smart.client.SessionUtils;
import com.smart.mvc.provider.IdProvider;
import com.smart.server.model.SysLog;
import com.smart.server.service.SysLogService;


/**
 * @Date : 2016/10/15
 */
@Aspect
@Component
public class SystemLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    private static final ThreadLocal<Date> beginTimeThreadLocal =
            new NamedThreadLocal<Date>("ThreadLocal beginTime");
   // private static final ThreadLocal<SysLog> logThreadLocal = 
    //        new NamedThreadLocal<SysLog>("ThreadLocal log");

    private static final ThreadLocal<SessionUser> currentUser=new NamedThreadLocal<SessionUser>("ThreadLocal user");

    @Autowired(required=false)
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    /**
     * Controller层切点 注解拦截
     */
    @Pointcut("@annotation(com.smart.server.common.aop.SystemControllerLog)")
    public void controllerAspect(){}

    //Service层切点    
    @Pointcut("@annotation(com.smart.server.common.aop.SystemServiceLog)")    
     public  void serviceAspect() {    
    }   
    /**
     * 前置通知 用于拦截Controller层记录用户的操作的开始时间
     * @param joinPoint 切点
     * @throws InterruptedException 
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException{
        Date beginTime=new Date();
        beginTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）  
        if (logger.isDebugEnabled()){//这里日志级别为debug
            logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .format(beginTime), request.getRequestURI());
        }

        //读取session中的用户 
        SessionUser sessionUser = SessionUtils.getSessionUser(request);    
        currentUser.set(sessionUser);

    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
    	SessionUser sessionUser = currentUser.get();
        if(sessionUser !=null){
            String title="";
            String type="info";                       //日志类型(info:入库,error:错误)
            String remoteAddr=request.getRemoteAddr();//请求的IP
            String requestUri=request.getRequestURI();//请求的Uri
            String method=request.getMethod();        //请求的方法类型(post/get)
            Map<String,String[]> params=request.getParameterMap(); //请求提交的参数

            try {
                title=getControllerMethodDescription(joinPoint);
            } catch (Exception e) {
                e.printStackTrace();
            }    
            // 打印JVM信息。
            long beginTime = beginTimeThreadLocal.get().getTime();//得到线程绑定的局部变量（开始时间） 
            long endTime = System.currentTimeMillis();  //2、结束时间  

            SysLog log=new SysLog();
            log.setLogId(IdProvider.createUUIDId());
            log.setTitle(title);
            log.setType(type);
            log.setRemoteAddr(remoteAddr);
            log.setRequestUri(requestUri);
            log.setMethod(method);
            log.setMapToParams(params);
            log.setUserId(sessionUser.getAccount());
            log.setOperateDate(new Date());
            log.setTimeout(String.valueOf(endTime-beginTime)+"ms");

            //1.直接执行保存操作
            this.sysLogService.insertSysControllerLog(log);

            //2.优化:异步保存日志
            //new SaveLogThread(log, logService).start();

            //3.再优化:通过线程池来执行日志保存
            //threadPoolTaskExecutor.execute(new SaveLogThread(log, sysLogService));
            //logThreadLocal.set(log);
        }

    }
    
    /**  
     * 异常通知 用于拦截service层记录异常日志  
     *  
     * @param joinPoint  
     * @param e  
     */    
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")    
     public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    
        SessionUser sessionUser = SessionUtils.getSessionUser(request);    
        //获取请求ip    
        String ip = request.getRemoteAddr();    
        if(sessionUser !=null){
            String title="";
            String type="error";                       //日志类型(info:入库,error:错误)
            String remoteAddr=request.getRemoteAddr();//请求的IP
            String requestUri=request.getRequestURI();//请求的Uri
            String method=request.getMethod();        //请求的方法类型(post/get)
            Map<String,String[]> params=request.getParameterMap(); //请求提交的参数

                try {
					title=getServiceMthodDescription(joinPoint);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            // 打印JVM信息。
            long beginTime = beginTimeThreadLocal.get().getTime();//得到线程绑定的局部变量（开始时间） 
            long endTime = System.currentTimeMillis();  //2、结束时间  

            SysLog log=new SysLog();
            log.setLogId(IdProvider.createUUIDId());
            log.setTitle(title);
            log.setType(type);
            log.setRemoteAddr(remoteAddr);
            log.setRequestUri(requestUri);
            log.setMethod(method);
            log.setMapToParams(params);
            log.setUserId(sessionUser.getAccount());
            log.setOperateDate(new Date());
            log.setTimeout(String.valueOf(endTime-beginTime)+"ms");
            log.setException(e.getMessage());
            //1.直接执行保存操作
            this.sysLogService.insertSysControllerLog(log);
    
    }    
    }
    



    /**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemControllerLog. class).description();    
                     break;    
                }    
            }    
        }    
         return description;    
    }    
     /**  
      * 获取注解中对方法的描述信息 用于service层注解  
      *  
      * @param joinPoint 切点  
      * @return 方法描述  
      * @throws Exception  
      */    
      public  static String getServiceMthodDescription(JoinPoint joinPoint)    
              throws Exception {    
         String targetName = joinPoint.getTarget().getClass().getName();    
         String methodName = joinPoint.getSignature().getName();    
         Object[] arguments = joinPoint.getArgs();    
         Class targetClass = Class.forName(targetName);    
         Method[] methods = targetClass.getMethods();    
         String description = "";    
          for (Method method : methods) {    
              if (method.getName().equals(methodName)) {    
                 Class[] clazzs = method.getParameterTypes();    
                  if (clazzs.length == arguments.length) {    
                     description = method.getAnnotation(SystemServiceLog. class).description();    
                      break;    
                 }    
             }    
         }    
          return description;    
     }    
   

}
