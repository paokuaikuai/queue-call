package com.smart.server.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.mvc.controller.BaseController;
import com.smart.mvc.model.Result;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.server.common.aop.SystemControllerLog;
import com.smart.server.model.Role;
import com.smart.server.model.RolePermission;
import com.smart.server.service.RolePermissionService;
import com.smart.server.service.RoleService;

/**
 * @author WS
 */
@Api(tags = "角色管理")
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

	@Resource
	private RoleService roleService;
	@Resource
	private RolePermissionService rolePermissionService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/role";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		Role role;
		if (id == null) {
			role = new Role();
		}
		else {
			role = roleService.get(id);
		}
		model.addAttribute("role", role);
		return "/admin/roleEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(
			@ApiParam(value = "角色名")String name,
			@ApiParam(value = "应用ID ") Integer appId,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(roleService.findPaginationByName(name, appId, new Pagination<Role>(pageNo, pageSize)));
	}

	@ApiOperation("启用/禁用")
	@SystemControllerLog(description="角色启用/禁用")
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody Result enable(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		roleService.enable(isEnable, getAjaxIds(ids));
		return Result.createSuccessResult();
	}

	@ApiOperation("新增/修改提交")
	@SystemControllerLog(description="角色新增/修改")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "角色名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String name,
			@ApiParam(value = "排序", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer sort,
			@ApiParam(value = "描述") String description,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		Role role;
		if (id == null) {
			role = new Role();
		}
		else {
			role = roleService.get(id);
		}
		role.setName(name);
		role.setSort(sort);
		role.setDescription(description);
		role.setIsEnable(isEnable);
		roleService.save(role);
		return Result.createSuccessResult();
	}
	
	@ApiOperation("角色权限对应关系数据")
	@SystemControllerLog(description="角色权限对应关系数据")
	@RequestMapping(value = "/allocate", method = RequestMethod.GET)
	public @ResponseBody Result allocate(
			@ApiParam(value = "角色id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer roleId) {
		return Result.createSuccessResult().setData(rolePermissionService.findByRoleId(roleId));
	}
	
	@ApiOperation("角色授权提交")
	@SystemControllerLog(description="角色授权提交")
	@RequestMapping(value = "/allocateSave", method = RequestMethod.POST)
	public @ResponseBody Result allocateSave(
			@ApiParam(value = "角色id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer roleId,
			@ApiParam(value = "权限ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String permissionIds) {
		List<Integer> idList = getAjaxIds(permissionIds);
		List<RolePermission> list = new ArrayList<RolePermission>();
		Integer permissionId;
		for (Iterator<Integer> i$ = idList.iterator(); i$.hasNext(); list.add(new RolePermission(roleId, permissionId))) {
			permissionId = i$.next();
		}
		rolePermissionService.allocate(roleId, list);
		return Result.createSuccessResult().setMessage("授权成功");
	}

	@ApiOperation("删除")
	@SystemControllerLog(description="角色删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		roleService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}

}