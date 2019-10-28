package com.datangedu.cn.servicegps;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.ProviderProduct;

public interface ProviderService {
	public List<ProviderProduct> getProviderInfoByld();

	int setProviderRegister(HttpServletRequest request);

}
