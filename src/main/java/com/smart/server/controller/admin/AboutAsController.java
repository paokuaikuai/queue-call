package com.smart.server.controller.admin;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "关于我们")
@Controller
@RequestMapping("/admin/aboutUs")
public class AboutAsController {
	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute() {
		return "admin/aboutUs";
	}

	    
}
