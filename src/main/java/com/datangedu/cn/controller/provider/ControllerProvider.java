package com.datangedu.cn.controller.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.model.sysUser.Provider;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.Region;
import com.datangedu.cn.model.sysUser.SysUser;
import com.datangedu.cn.servicegps.ProviderUserService;
import com.datangedu.cn.servicegps.RegionService;
import com.datangedu.cn.servicegps.StoreOrderService;


@Controller
@RequestMapping("/provider")
public class ControllerProvider {
	
	@Resource
	ProviderUserService providerUserService;
	@Resource
	RegionService rService;
	
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
	@RequestMapping(value = "/informationUpdate", method = RequestMethod.POST)
	public Map<String, Object> provideRegister(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		int massageInfo = provideOrderService.setInformationUpdate(request);
		if (massageInfo == 4) {
			map.put("msg", "输入有误");
			return map;
		} else {
			map.put("msg", "保存成功");
		}
		return map;
	}
	
	
	@Resource
	ProviderUserService providerService;
	@ResponseBody
	@RequestMapping(value = "/getprovider", method = RequestMethod.GET)
	public Map<String, Object> ProviderInfo(HttpServletRequest request) {
		System.out.println("666");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Provider> provider = providerService.getProviderId(request.getParameter("loginId"));
		System.out.println(provider.get(0).getName());
		
		//把地区代号改为名字
		String[] str = new String[3];
		String str8 = "";
		List<Region> rList = rService.getList(provider.get(0).getRegionId());
		str[2] = rList.get(0).getName();

		if (rList.get(0).getLevel() == 3) {
			rList = rService.getList(rList.get(0).getParentId());
			str[1] = rList.get(0).getName();
		}

		if (rList.get(0).getLevel() == 2) {
			rList = rService.getList(rList.get(0).getParentId());
			str[0] = rList.get(0).getName();
		}
		str8 = str[0] + " " + str[1] + " " + str[2];
		str[0] = "";
		str[1] = "";
		str[2] = "";
		System.out.println(str8);
		provider.get(0).setRegionId(str8);
		
		map.put("provider", provider);
		return map;
	}
	
	@ResponseBody	
	@RequestMapping(value="/headImg", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> headImg(String id) throws Exception{
		System.out.println("tp");
		byte[] imageContent ;
		// 根据id获取当前用户的信息
		Provider provider = providerUserService.getProviderUserInfo(id);
				        
		imageContent = provider.getProviderImg();
		System.out.println("图片==="+provider.getProviderImg());
				        
		// 设置http头部信息
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		// 返回响应实体
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
}
