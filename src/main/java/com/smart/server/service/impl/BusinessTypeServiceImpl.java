package com.smart.server.service.impl;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;

import com.smart.server.dao.BusinessTypeDao;
import com.smart.server.model.BusinessType;
import com.smart.server.service.BusinessTypeService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessTypeServiceImpl extends ServiceImpl<BusinessTypeDao, BusinessType, Integer> implements BusinessTypeService {

    @Autowired
    public void setDao(BusinessTypeDao dao) {
        this.dao = dao;
    }

    public Pagination<BusinessType> findPagination(Integer departId, Pagination<BusinessType> p) {
        dao.findPagination(departId, p);
        return p;
    }

    public List<BusinessType> findByDepartId(Integer departId) {
        Pagination<BusinessType> p = new Pagination<BusinessType>();
        p.setPageNo(1);
        p.setPageSize(Integer.MAX_VALUE);
        dao.findPagination(departId, p);
        return p.getList();
    }
}
