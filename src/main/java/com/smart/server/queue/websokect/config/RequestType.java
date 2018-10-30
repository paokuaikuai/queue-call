package com.smart.server.queue.websokect.config;

/**
 * websocket请求类型
 */
public class RequestType {

    //-------------------添加页面---------------------
    //请求为添加操作
    public static final String ADD = "add";

    //请求为获取列表内容操作
    public static final String LIST = "list";
    //-----------------------------------------------


    //--------------------呼叫页面--------------------
    //请求为获取之前呼叫的内容操作
    public static final String CALLED = "called";

    //当前等待人
    public static final String WAITING = "waiting";

    //请求为获取之前呼叫的内容操作
    public static final String CALLING = "calling";

    //重呼
    public static final String RECALL = "recall";

    //请求为获取过号内容操作
    public static final String SKIP = "skip";
    //-----------------------------------------------

    //屏幕
    public static final String SCREEN = "screen";

    //请求为未知操作
    public static final String UNKONW = "unkonw";
}
