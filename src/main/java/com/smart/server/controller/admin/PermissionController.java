package com.smart.server.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.mvc.controller.BaseController;
import com.smart.mvc.model.Result;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.server.common.aop.SystemControllerLog;
import com.smart.server.model.Permission;
import com.smart.server.service.PermissionService;

/**
 * @author WS
 */
@Api(tags = "权限(含菜单)管理")
@Controller
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController {

	@Resource
	private PermissionService permissionService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/permission";
	}

	@ApiOperation("权限树节点")
	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public @ResponseBody List<Permission> nodes(
			@ApiParam(value = "名称") String name,
			@ApiParam(value = "是否启用 ") Boolean isEnable) {
		List<Permission> list = permissionService.findByName(name, isEnable);
		Permission permission = new Permission();
		permission.setId(null);
		permission.setParentId(-1);
		permission.setName("根节点");
		list.add(0, permission);
		return list;
	}

	@ApiOperation("新增/修改提交")
	@SystemControllerLog(description="权限树新增/修改")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "父id", required = true) Integer parentId,
			@ApiParam(value = "图标") String icon,
			@ApiParam(value = "名称", required = true) @ValidateParam({ Validator.NOT_BLANK }) String name,
			@ApiParam(value = "权限URL", required = true) @ValidateParam({ Validator.NOT_BLANK }) String url,
			@ApiParam(value = "排序", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer sort,
			@ApiParam(value = "是否菜单", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isMenu,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		Permission permission;
		if (id == null) {
			permission = new Permission();
		}
		else {
			permission = permissionService.get(id);
		}
		permission.setParentId(parentId);
		permission.setIcon(icon);
		permission.setName(name);
		permission.setUrl(url);
		permission.setSort(sort);
		permission.setIsMenu(isMenu);
		permission.setIsEnable(isEnable);
		permissionService.save(permission);
		return Result.createSuccessResult().setMessage("保存成功");
	}

	@ApiOperation("删除")
	@SystemControllerLog(description="权限树删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			@ApiParam(value = "id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer id) {
		permissionService.deletePermission(id);
		return Result.createSuccessResult().setMessage("删除成功");
	}

}