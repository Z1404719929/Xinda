package com.datangedu.cn.servicegps;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.ProviderProduct;

public interface ProviderProductService {
	public List<ProviderProduct> getpp();

	public List<ProviderProduct> getid(String id);

}
