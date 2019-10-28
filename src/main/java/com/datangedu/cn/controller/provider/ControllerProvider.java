package com.datangedu.cn.controller.provider;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.datangedu.cn.servicegps.StoreOrderService;

@Controller
@RequestMapping("/provider")
public class ControllerProvider {
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
