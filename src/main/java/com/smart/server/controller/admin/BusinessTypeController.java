package com.smart.server.controller.admin;

import com.smart.mvc.controller.BaseController;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.model.Result;
import com.smart.mvc.validator.Validator;
import com.smart.mvc.validator.annotation.ValidateParam;
import com.smart.server.common.aop.SystemControllerLog;
import com.smart.server.model.BusinessType;
import com.smart.server.model.Department;
import com.smart.server.service.BusinessTypeService;
import com.smart.server.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author YJ
 */
@Api(tags = "业务类型管理")
@Controller
@RequestMapping("/admin/business")
public class BusinessTypeController extends BaseController {

    @Resource
    private BusinessTypeService businessTypeService;

    @Resource
    private DepartmentService departmentService;

    @ApiOperation("初始页")
    @RequestMapping(method = RequestMethod.GET)
    public String execute(Model model) {
        model.addAttribute("department", departmentService.findAll());
        return "/admin/business";
    }

    @ApiOperation("新增/修改页")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@ApiParam(value = "id") Integer id, Model model) {
        BusinessType businessType;
        if (id == null) {
            businessType = new BusinessType();
        } else {
            businessType = businessTypeService.get(id);
        }
        model.addAttribute("businessType", businessType);
        model.addAttribute("department", departmentService.findAll());
        return "/admin/businessEdit";
    }

    @ApiOperation("列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    Result list(
            @ApiParam(value = "科室Id", required = true) Integer departId,
            @ApiParam(value = "开始页码", required = true) @ValidateParam({Validator.NOT_BLANK}) Integer pageNo,
            @ApiParam(value = "显示条数", required = true) @ValidateParam({Validator.NOT_BLANK}) Integer pageSize,
            Model model) {
        return Result.createSuccessResult().setData(businessTypeService.findPagination(departId, new Pagination<BusinessType>(pageNo, pageSize)));
    }


    @ApiOperation("新增/修改提交")
    @SystemControllerLog(description = "业务类型新增/修改")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Result save(
            @ApiParam(value = "id") Integer id,
            @ApiParam(value = "名称") String name,
            @ApiParam(value = "科室Id") Integer departId) {
        BusinessType businessType = new BusinessType();
        businessType.setId(id);
        businessType.setName(name);
        businessType.setDepartId(departId);
        if (businessType.getId() != null) {
            businessTypeService.update(businessType);
        } else {
            businessTypeService.save(businessType);
        }
        return Result.createSuccessResult();
    }


    @ApiOperation("删除")
    @SystemControllerLog(description = "业务类型删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Result delete(
            @ApiParam(value = "ids", required = true) @ValidateParam({Validator.NOT_BLANK}) String ids) {
        businessTypeService.deleteById(getAjaxIds(ids));
        return Result.createSuccessResult();
    }

}