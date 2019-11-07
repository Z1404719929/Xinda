package com.datangedu.cn.controller.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.model.sysUser.Provider;
import com.datangedu.cn.model.sysUser.SysUser;
import com.datangedu.cn.servicegps.ProviderUserService;
import com.datangedu.cn.servicegps.StoreOrderService;


@Controller
@RequestMapping("/provider")
public class ControllerProvider {
	
	@Resource
	ProviderUserService providerUserService;
	
	@ResponseBody
	@RequestMapping(value = "/providerlogin",method = RequestMethod.POST)
	public Map <String,Object> providerLogin(HttpServletRequest request) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(request.getParameter("loginId"));
		System.out.println(request.getParameter("password"));
		String loginId=request.getParameter("loginId");
		if(loginId.isEmpty()) {
			map.put("msg","输入登录账号" );
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
		
		
		List<Provider> providerUserInfo = providerUserService.setProviderLogin(request);
		if(providerUserInfo.isEmpty()) {
			map.put("msg","账号不存在" );
			return map;
		}
		
		if(!MD5Util.getMD5(request.getParameter("password").getBytes()).equals(providerUserInfo.get(0).getPassword())) {
			map.put("msg", "密码不正确");
			return map;
		}
		HttpSession session=request.getSession();
		if(!request.getParameter("code").equals(session.getAttribute("code"))) {
			map.put("msg", "验证码错误");
			return map;

		}
		
		System.out.println(providerUserInfo.get(0).getName());
		map.put("providerid",providerUserInfo.get(0).getId());
		map.put("providername",providerUserInfo.get(0).getName());
		map.put("code", 1);
		return map;
}
	
	@ResponseBody
	@RequestMapping(value = "/findpassword",method = RequestMethod.POST)
	public Map <String,Object> findPassword(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		Provider provideruser=new Provider();
		
		if(request.getParameter("loginId").isEmpty()) {
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
		provideruser.setCellphone(request.getParameter("loginId"));
		provideruser.setPassword(MD5Util.getMD5(request.getParameter("password1").getBytes()));
		int a=providerUserService.updatepassword(provideruser, request);
		System.out.println("修改密码5"+a);
		map.put("stu", a);
		if(a==1) {
		map.put("msg", "修改成功");
		}else {
			map.put("msg", "账号不存在");
		}
		return map;
	}
	
	
	
	
	@Resource
	StoreOrderService providerOrderService;
	@ResponseBody
	@RequestMapping(value = "/storeregister", method = RequestMethod.POST)
	public Map<String, Object> providerRegister(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		int orderInfo = providerOrderService.setStoreRegister(request);
		if (orderInfo == 3) {
			map.put("msg", "输入有误");
			return map;
		} else {
			map.put("msg", "保存成功");
		}
		return map;
	}
	
	
	@Resource
	StoreOrderService provideOrderService;
	@ResponseBody
	@RequestMapping(value = "/massageregister", method = RequestMethod.POST)
	public Map<String, Object> provideRegister(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		int massageInfo = provideOrderService.setMassageRegister(request);
		if (massageInfo == 4) {
			map.put("msg", "输入有误");
			return map;
		} else {
			map.put("msg", "保存成功");
		}
		return map;
	}
}
