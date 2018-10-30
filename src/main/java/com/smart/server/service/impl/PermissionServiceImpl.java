package com.smart.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.rpc.RpcPermission;
import com.smart.server.common.aop.SystemControllerLog;
import com.smart.server.common.aop.SystemServiceLog;
import com.smart.server.dao.PermissionDao;
import com.smart.server.model.Permission;
import com.smart.server.service.PermissionJmsService;
import com.smart.server.service.PermissionService;
import com.smart.server.service.RolePermissionService;

@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission, Integer> implements PermissionService {

	@Resource
	private RolePermissionService rolePermissionService;
	@Resource
	private PermissionService permissionService;
	@Resource
	private PermissionJmsService permissionJmsService;

	@Autowired
	public void setDao(PermissionDao dao) {
		this.dao = dao;
	}
	@SystemServiceLog(description="保存权限树节点")
	public void save(Permission t) {
		super.save(t);
		// JMS通知权限变更
		//permissionJmsService.send(appService.get(t.getAppId()).getCode());
	}
	
	@SystemServiceLog(description="查看权限树节点")
	public List<Permission> findByName(String name, Boolean isEnable) {
		return dao.findByName(name, isEnable);
	}
    
	@Transactional
	@SystemServiceLog(description="删除权限树节点")
	public void deletePermission(Integer id) {
		List<Integer> idList = new ArrayList<Integer>();

		List<Permission> list = permissionService.findByName(null, null);
		loopSubList(id, idList, list);
		idList.add(id);

		rolePermissionService.deleteByPermissionIds(idList);

		verifyRows(dao.deleteById(idList), idList.size(), "权限数据库删除失败");
	}

	// 递归方法，删除子权限
	protected void loopSubList(Integer id, List<Integer> idList, List<Permission> list) {
		for (Permission p : list) {
			if (id.equals(p.getParentId())) {
				idList.add(p.getId());
				loopSubList(p.getId(), idList, list);
			}
		}
	}

	public void deleteByAppIds(List<Integer> idList) {
		dao.deleteByAppIds(idList);
	}

	public List<RpcPermission> findListById(Integer userId) {
		return dao.findListById(userId);
	}
}
