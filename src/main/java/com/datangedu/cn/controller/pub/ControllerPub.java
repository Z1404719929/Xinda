package com.datangedu.cn.controller.pub;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ControllerPub {
	@RequestMapping("/redirect")
	public String page(HttpServletRequest request) {
		String url=request.getParameter("page");
		System.out.println("tiaozhuan"+url);
		return url;
	}

}
