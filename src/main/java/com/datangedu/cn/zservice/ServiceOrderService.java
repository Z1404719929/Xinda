package com.datangedu.cn.zservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.ServiceOrder;


public interface ServiceOrderService {
	public List<ServiceOrder> getso();

	public List<ServiceOrder> select(HttpServletRequest request);

	List<ServiceOrder> getpay();

	List<ServiceOrder> getuid(String userid);

	public int up(HttpServletRequest request);
	int insertServiceOrder(HttpServletRequest request);


	List<ServiceOrder> getll(String userid);

	public int pProductDelete(HttpServletRequest request);

	public int pProductup(HttpServletRequest request);

	List<ServiceOrder> getmm(String serciceNo);
	
}
