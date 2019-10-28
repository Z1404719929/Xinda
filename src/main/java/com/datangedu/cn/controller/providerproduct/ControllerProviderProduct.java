package com.datangedu.cn.controller.providerproduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.servicegps.ProviderService;

@Controller
@RequestMapping("/provider")
public class ControllerProviderProduct {
	@Resource
	ProviderService providerProductService;

	@ResponseBody
	@RequestMapping(value = "/getproviderinfo", method = RequestMethod.GET)
	public Map<String, Object> ProviderInfo() {
		System.out.println("1111");
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProviderProduct> providerInfo = providerProductService.getProviderInfoByld();
		map.put("providerInfo", providerInfo);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/providerregister", method = RequestMethod.POST)
	public Map<String, Object> providerRegister(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		int providerInfo = providerProductService.setProviderRegister(request);
		if (providerInfo == 2) {
			map.put("msg", "输入有误");
		} else {
			map.put("msg", "添加成功");
		}
		return map;
	}

}
