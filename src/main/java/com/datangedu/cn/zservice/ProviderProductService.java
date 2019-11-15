package com.datangedu.cn.zservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.Cart;
import com.datangedu.cn.model.sysUser.ProviderProduct;

public interface ProviderProductService {
	public List<ProviderProduct> getpp();
	public List<ProviderProduct> getid(String id);
	public int xx(ProviderProduct pp,String id);	//修改状态下线
	public int sx(ProviderProduct pp,String id);//修改状态上线
	public List<ProviderProduct> getProviderProductInfoById();
	public List<ProviderProduct> select(HttpServletRequest request); 	//模糊查询
	List<ProviderProduct> selectAll(HttpServletRequest request);
	List<ProviderProduct> selectpaging(HttpServletRequest request);
	int check1(HttpServletRequest request);
	int check2(HttpServletRequest request);
	public int del(HttpServletRequest request);
	ProviderProduct getUserInfo(String id);
}
