package com.datangedu.cn.controller.sysuser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.model.sysUser.SysUser;
import com.datangedu.cn.zservice.SysUserService;

@Controller
@RequestMapping("/su")
public class ControllerSysUser {
	
	@Resource
	SysUserService sysUserService;
	
	@ResponseBody
	@RequestMapping(value = "/sysuserlogin",method = RequestMethod.POST)
	public Map <String,Object> userLogin(HttpServletRequest request) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		//userInfo.get(0).getId();
		System.out.println("123"+request.getParameter("cellphone"));
		System.out.println("123"+request.getParameter("password"));
		String cellphone=request.getParameter("cellphone");
		if(cellphone.isEmpty()) {
			map.put("msg","输入手机号" );
			return map;
		}
		if(request.getParameter("password").isEmpty()) {
			map.put("msg","输入密码" );
			return map;
		}
		if(request.getParameter("code").isEmpty()) {
			map.put("msg","输入验证码" );
			return map;
		}
		
		
		List<SysUser> userInfo = sysUserService.setUserLogin(request);
		if(userInfo.isEmpty()) {
			map.put("msg","账号不存在" );
			return map;
		}
		
		System.out.println(userInfo.get(0).getUserName());
		map.put("userid",userInfo.get(0).getId());
		map.put("username",userInfo.get(0).getUserName());
		map.put("msg","登陆成功");
		map.put("code", 1);
		
/*//		 System.out.println("用户id"+userInfo.get(0).getId());
//		map.put("id",userInfo.get(0).getId());
// 	map.put("userId",userInfo.get(0).getId());
		
//		map.put("sysuser",request.getParameter("cellphone"));
//		map.put("msg","登陆成功");
//		map.put("code", 1);*/
		return map;
}
	
	@ResponseBody
	@RequestMapping(value = "/findpassword",method = RequestMethod.POST)
	public Map <String,Object> findPassword(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		SysUser su=new SysUser();
		
		if(request.getParameter("cellphone").isEmpty()) {
			map.put("msg","输入手机号" );
			return map;
		}
		if(request.getParameter("code").isEmpty()) {
			map.put("msg","输入验证码" );
			return map;
		}
		if(request.getParameter("password1").isEmpty()) {
			map.put("msg","输入密码" );
			return map;
		}
		if(request.getParameter("password2").isEmpty()) {
			map.put("msg","再输入密码" );
			return map;
		}
		if(!request.getParameter("password1").equals(request.getParameter("password2"))) {
			map.put("msg","两次密码不一致");
			return map;
		}
		su.setCellphone(request.getParameter("cellphone"));
		su.setPassword(MD5Util.getMD5(request.getParameter("password1").getBytes()));
		int a=sysUserService.updatepassword(su, request);
		System.out.println("修改密码5"+a);
		map.put("stu", a);
		if(a==1) {
		map.put("msg", "修改成功");
		}else {
			map.put("msg", "账号不存在");
		}
		return map;
	}
	
	
	
	
}
