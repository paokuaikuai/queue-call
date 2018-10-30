package com.smart.server.dao;


import com.smart.mvc.model.Pagination;
import com.smart.server.model.SysLog;

import java.util.List;

public interface SysLogDao {
    //新增
    public Long insert(SysLog SysLog);


    List<SysLog> selectLog(Pagination<SysLog> p);

}