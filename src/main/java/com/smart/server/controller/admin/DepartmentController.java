package com.smart.server.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;

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
import com.smart.server.model.Department;
import com.smart.server.service.DepartmentService;

/**
 * @author WS
 */
@Api(tags = "科室管理")
@Controller
@RequestMapping("/admin/department")
public class DepartmentController extends BaseController {

	@Resource
	private DepartmentService departmentService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/department";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		Department department;
		if (id == null) {
			department = new Department();
		}
		else {
			department = departmentService.get(id);
		}
		model.addAttribute("department", department);
		return "/admin/departmentEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(
			@ApiParam(value = "科室名称", required = true) String departName,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(departmentService.findPaginationByName(departName, new Pagination<Department>(pageNo, pageSize)));
	}


	@ApiOperation("新增/修改提交")
	@SystemControllerLog(description="角色新增/修改")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "id")Integer id,
			@ApiParam(value = "编码")String departCode,
			@ApiParam(value = "科室名称", required = true) @ValidateParam({ Validator.NOT_BLANK }) String departName,
			@ApiParam(value = "科室类型", required = true) @ValidateParam({ Validator.INT }) Integer departType,
			@ApiParam(value = "首字母")String firstNum,
			@ApiParam(value = "屏号") String screenNum,
			@ApiParam(value = "备注")String remark) {
		Department department;
		if (id == null) {
			department = new Department();
			department.setCreateTime(new Date());
		}
		else {
			department = departmentService.get(id);
		}
		department.setDepartCode(departCode);
		department.setDepartName(departName);
		department.setDepartType(departType);
		department.setFirstNum(firstNum);
		department.setScreenNum(screenNum);
		department.setRemark(remark);
		department.setUpdateTime(new Date());
		departmentService.save(department);
		return Result.createSuccessResult();
	}
	

	@ApiOperation("删除")
	@SystemControllerLog(description="科室删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		departmentService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}

}