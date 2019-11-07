package com.datangedu.cn.zservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.ServiceOrder;


public interface ServiceOrderService {
	public List<ServiceOrder> getso();

	public List<ServiceOrder> select(HttpServletRequest request);

	List<ServiceOrder> getpay();
}
