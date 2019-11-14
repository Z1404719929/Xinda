package com.datangedu.cn.servicegps;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.Provider;

public interface StoreOrderService {

	public int setStoreRegister(HttpServletRequest request);
	
	public int setInformationUpdate(HttpServletRequest request);
}
