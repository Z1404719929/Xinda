package com.datangedu.cn.controller.cart;
import java.util.ArrayList;
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
import com.datangedu.cn.zservice.CartService;
import com.datangedu.cn.zservice.ProviderProductService;
@Controller
@RequestMapping("/product")
public class ControllerCart {
	 @Resource 
	 CartService  cartService;
	 @Resource 
	 ProviderProductService  providerProductService;
//将网页查询到的id插入购物车
	@ResponseBody
	@RequestMapping(value="/jkk",method=RequestMethod.POST)
	public Map<String,Object> ProductAdd(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String,Object>();
		List<Cart> cartInfo=cartService.getcid(request.getParameter("id"));
		if(cartInfo.isEmpty()) {
			int a = cartService.insertCart(request);		
			System.out.println("插入成功"+a);
		map.put("msg",a );
		return map;
		}
	
		if(!cartInfo.isEmpty()) {
			map.put("msg","id已存在" );
			Cart cart=new Cart();
			cart.setBuyNum(cartInfo.get(0).getBuyNum()+1);
			int a=cartService.updateBuynum(cart, request.getParameter("id"));
		}

		return map;	
	}
	
	// 根据购物车里商品id返回商品信息
	@ResponseBody
	@RequestMapping(value="/getproductinfo",method=RequestMethod.POST)
	public Map<String,Object> Provideproduct(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String,Object>();
		System.out.println("156655");
		List<Cart> cartInfo=cartService.getcartid(request);
		System.out.println("156655");
		List<Map<String ,Object>> product =  new ArrayList<Map<String, Object>>();
		
		System.out.println("156655");
		
//		List<ProviderProduct> providerProductInfo = null;
		for(int i=0;i<cartInfo.size();i++) {
		String id=cartInfo.get(i).getServiceId();
		System.out.println("1555");
		List<ProviderProduct> providerProductInfo=providerProductService.getid(id);
		System.out.println("8888888");
//		providerProductInfo.add((ProviderProduct)providerProductService.getid(id));
		if(product.isEmpty()) {
			System.out.println("99999");
			product.get(0).put("name",providerProductInfo.get(0).getProviderName() );
			product.get(0).put("product",providerProductInfo);
			System.out.println("255555");
		}

		System.out.println("14777"+product.get(0)); 
		map.put("providerProductInfo",providerProductInfo);
		System.out.println("map"+map);
		}

		return map ;
	}
	
}
