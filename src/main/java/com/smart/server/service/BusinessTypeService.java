package com.smart.server.service;


import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;
import com.smart.server.model.BusinessType;
import java.util.List;

/**
 * 业务类型服务接口
 *
 * @author YJ
 */
public interface BusinessTypeService extends Service<BusinessType, Integer> {

    /**
     * 查询分页列表
     *
     * @return
     */
    Pagination<BusinessType> findPagination(Integer departId, Pagination<BusinessType> p);

    List<BusinessType> findByDepartId(Integer departId);

}
