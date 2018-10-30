package com.smart.server.controller.admin;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.smart.mvc.config.ConfigUtils;
import com.smart.mvc.model.Result;
import com.smart.server.queue.QueueCollection;
import com.smart.server.service.BusinessTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smart.client.SessionUser;
import com.smart.client.SessionUtils;
import com.smart.mvc.controller.BaseController;
import com.smart.server.model.Department;
import com.smart.server.model.Room;
import com.smart.server.service.DepartmentService;
import com.smart.server.service.RoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author WS
 */
@Api(tags = "功能科室排队")
@Controller
@RequestMapping("/queue")
public class QueueFunctionController extends BaseController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private RoomService roomService;


    @Resource
    private BusinessTypeService businessTypeService;

    @ApiOperation("排队页面")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
        SessionUser sessionUser = SessionUtils.getSessionUser(request);
        Integer departId = sessionUser.getDepartId();
        //查询登陆人科室名称
        Department department = departmentService.get(departId);
        model.addAttribute("departmentName", department.getDepartName());
        model.addAttribute("departmentId", department.getId());
        //查询诊室
        List<Room> listRoom = roomService.findRoomByDepartId(departId);
        model.addAttribute("listRoom", listRoom);
        model.addAttribute("business", businessTypeService.findByDepartId(department.getId()));

        return "queue/list";
    }

    @ApiOperation("呼叫页面")
    @RequestMapping(value = "/call", method = RequestMethod.GET)
    public String call(HttpServletRequest request, Model model) {
        SessionUser sessionUser = SessionUtils.getSessionUser(request);
        Integer roomId = sessionUser.getRoomId();
        Room room = roomService.get(roomId);
        model.addAttribute("roomName", room.getRoomName());
        return "queue/call";
    }

    @ApiOperation("屏幕页面")
    @RequestMapping(value = "/screen", method = RequestMethod.GET)
    public String screen(HttpServletRequest request, Model model) {
        SessionUser sessionUser = SessionUtils.getSessionUser(request);
        Department department = departmentService.get(sessionUser.getDepartId());
        model.addAttribute("department", department);
        model.addAttribute("hospital", ConfigUtils.getProperty("system.hospital"));
        return "queue/screen";
    }

    @ApiOperation("管理页面")
    @RequestMapping(value = "/maneger", method = RequestMethod.GET)
    public String maneger(HttpServletRequest request, Model model) {
        return "queue/maneger";
    }

    @ApiOperation("管理页面")
    @RequestMapping(value = "/clear")
    public @ResponseBody
    Result clear(HttpServletRequest request, Model model) {
        QueueCollection.newInstance().clear();
        return Result.createSuccessResult();
    }
}
