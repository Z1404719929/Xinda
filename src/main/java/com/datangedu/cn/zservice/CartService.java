package com.datangedu.cn.zservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.Cart;
import com.datangedu.cn.model.sysUser.Member;
import com.datangedu.cn.model.sysUser.ProviderProduct;

public interface CartService {


	int insertCart(HttpServletRequest request);
	List<Cart> getcid(String id);
	public int updateBuynum(Cart cart,String id);
	List<Cart> getcartid(HttpServletRequest request);
	List<Cart> getuid(String userid);
	int providerProductDelete(HttpServletRequest request);
	public int updateBuynum1(Cart cart,HttpServletRequest request);
	List<Cart> getid(HttpServletRequest request, String id);

}
