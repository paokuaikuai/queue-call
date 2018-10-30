package com.smart.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;
import com.smart.server.model.Department;
import com.smart.server.model.Role;

/**
 * 科室接口
 * 
 * @author WS
 */
public interface DepartmentDao extends Dao<Department, Integer> {

	public List<Department> findPaginationByName(@Param("departName") String departName,
			 Pagination<Department> p);

}
