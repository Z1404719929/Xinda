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

import com.datangedu.cn.model.sysUser.Cart;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.ProviderProductExample;
import com.datangedu.cn.model.sysUser.SysUser;
import com.datangedu.cn.zservice.CartService;
import com.datangedu.cn.zservice.ProviderProductService;
@Controller
@RequestMapping("/product")
public class ControllerProviderProduct3 {
	
	//显示所有商品
	 @Resource 
	 ProviderProductService  providerProductService;
	 @Resource 
	 CartService  cService;
	 
	@ResponseBody
	@RequestMapping(value="/gmgm",method=RequestMethod.POST)
	public Map<String,Object> Provideproduct(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String,Object>();
		//String id=request.getParameter("id");
		List<Cart> cart=cService.getcartid(request);
		map.put("cartnum", cart.size());
		List<ProviderProduct> providerProductInfo=providerProductService.getProviderProductInfoById(request);
		System.out.println(providerProductInfo); 
		map.put("providerProductInfo",providerProductInfo);
		System.out.println("map"+map);
		return map ;

	}
	
	@ResponseBody			//模糊查询
	@RequestMapping(value="/select",method = RequestMethod.GET)
	public Map <String,Object> selecte(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<ProviderProduct> providerProductInfo= providerProductService.select2(request);
		System.out.println(providerProductInfo); 
		map.put("providerProductInfo", providerProductInfo);
		return map;
	}
	

	
}
