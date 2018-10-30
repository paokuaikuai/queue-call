package com.smart.server.service;


import java.util.List;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;
import com.smart.server.model.Department;

/**
 * 科室服务接口
 * 
 * @author WS
 */
public interface DepartmentService extends Service<Department, Integer> {
	
	/**
	 * 查询分页列表
	 * @param pageNo 分页起始
	 * @param pageSize 分页记录数
	 * @return
	 */
	public Pagination<Department> findPaginationByName(String name,  Pagination<Department> p);
	
	/**
	 * 查询所有不分页
	 * @return
	 */
	public List<Department> findAll();
	
}
