package com.smart.server.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
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
import com.smart.server.model.Department;
import com.smart.server.model.Room;
import com.smart.server.service.DepartmentService;
import com.smart.server.service.RoomService;

/**
 * @author WS
 */
@Api(tags = "诊室管理")
@Controller
@RequestMapping("/admin/room")
public class RoomController extends BaseController {

	@Resource
	private RoomService roomService;
	@Resource
	private DepartmentService departmentService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/room";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		Room room;
		if (id == null) {
			room = new Room();
		}
		else {
			room = roomService.get(id);
		}
		List<Department> list = departmentService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("room", room);
		return "/admin/roomEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(
			@ApiParam(value = "诊室名称", required = true) String roomName,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(roomService.findPaginationByName(roomName, new Pagination<Room>(pageNo, pageSize)));
	}


	@ApiOperation("新增/修改提交")
	@SystemControllerLog(description="角色新增/修改")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "id")Integer id,
			@ApiParam(value = "科室ID")Integer departId,
			@ApiParam(value = "诊室编号", required = true) @ValidateParam({ Validator.NOT_BLANK }) String roomCode,
			@ApiParam(value = "诊室名称", required = true) @ValidateParam({ Validator.NOT_BLANK }) String roomName
			) {
		Room room;
		if (id == null) {
			room = new Room();
			room.setCreateTime(new Date());
		}
		else {
			room = roomService.get(id);
		}
		room.setDepartId(departId);
		room.setRoomCode(roomCode);
		room.setRoomName(roomName);
		room.setUpdateTime(new Date());
		roomService.save(room);
		return Result.createSuccessResult();
	}
	

	@ApiOperation("删除")
	@SystemControllerLog(description="科室删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		roomService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}

}