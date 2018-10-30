package com.smart.server.dao;

import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;
import com.smart.server.model.BusinessType;
import com.smart.server.model.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务类型接口
 *
 * @author YJ
 */
public interface BusinessTypeDao extends Dao<BusinessType, Integer> {

    List<Department> findPagination(@Param("departId") Integer departId, Pagination<BusinessType> p);
}
