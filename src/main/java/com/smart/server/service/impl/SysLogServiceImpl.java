package com.smart.server.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.smart.mvc.model.Pagination;
import com.smart.server.common.aop.SystemServiceLog;
import com.smart.server.dao.SysLogDao;
import com.smart.server.model.SysLog;
import com.smart.server.service.SysLogService;
@Service("sysLogService")
public class SysLogServiceImpl implements  SysLogService {

	@Resource
	private SysLogDao sysLogDao;
		@SystemServiceLog(description="查询日志列表")
		public Pagination<SysLog> selectLog(Pagination<SysLog> p) {
			sysLogDao.selectLog(p);
			return p;
		}

		public void insertSysControllerLog(SysLog runningLog) {
			sysLogDao.insert(runningLog);
			
		}
}
