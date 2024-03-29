package com.datangedu.cn.servicegps;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.ProviderProduct;

public interface ProviderProductService2 {
	public List<ProviderProduct> getProviderInfoByld(HttpServletRequest request);

	int setProviderRegister(HttpServletRequest request);

	int providerDown(ProviderProduct pp, String id);

	int providerOline(ProviderProduct pp, String id);
	
	public List<ProviderProduct> getid(String id);
	
	public int setProviderDelete(HttpServletRequest request);

	void saveServiceImg(ProviderProduct providerProduct) throws Exception;
	
	ProviderProduct getProviderInfoByld(String id);

	String setProviderApa(HttpServletRequest request);

	List<ProviderProduct> selectByLikeProductId(HttpServletRequest request);

}
