package com.smart.server.service;


import com.smart.mvc.model.Pagination;
import com.smart.server.model.SysLog;

public interface SysLogService {

	Pagination<SysLog> selectLog(Pagination<SysLog> p);

    void insertSysControllerLog(SysLog runningLog);
}
