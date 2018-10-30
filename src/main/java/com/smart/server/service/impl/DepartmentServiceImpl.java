package com.smart.server.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.server.common.aop.SystemServiceLog;
import com.smart.server.dao.DepartmentDao;
import com.smart.server.model.Department;
import com.smart.server.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, Department, Integer> implements DepartmentService {


	@Autowired
	public void setDao(DepartmentDao dao) {
		this.dao = dao;
	}
	@SystemServiceLog(description="保存")
	public void save(Department t) {
		super.save(t);
	}

	public Pagination<Department> findPaginationByName(String name, Pagination<Department> p) {
		dao.findPaginationByName(name, p);
		return p;
	}

	public List<Department> findAll() {
		return dao.findPaginationByName(null,null);
	}


}
