package com.datangedu.cn.service.Implgps;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.ProviderMapper;
import com.datangedu.cn.model.sysUser.Provider;
import com.datangedu.cn.servicegps.StoreOrderService;

@Service
public class StoreOrderServiceImpl implements StoreOrderService {
	@Resource
	ProviderMapper providerMapper;

	public int setStoreRegister(HttpServletRequest request) {
		// 校验信息
		System.out.println("sss"+request.getParameter("providerInfo").isEmpty());
		System.out.println("sss"+request.getParameter("providerInfo")+request.getParameter("workTime")+request.getParameter("qq")+request.getParameter("cellphone"));
		/*
		 * if (request.getParameter("providerInfo").isEmpty()||
		 * request.getParameter("workTime").isEmpty()||request.getParameter("qq").
		 * isEmpty()||request.getParameter("cellphone").isEmpty() ) {
		 * System.out.println("aaa"); return 3; }
		 */
		System.out.println("ooo");
		Provider provider=new Provider();
		provider.setId(request.getParameter("id"));
		provider.setCellphone(request.getParameter("cellphone"));
		provider.setProviderInfo(request.getParameter("providerInfo"));
		provider.setWorkTime(request.getParameter("workTime"));
		provider.setQq(request.getParameter("qq"));
		return providerMapper.updateByPrimaryKeySelective(provider);
	}
	
	
	
	public int setInformationUpdate(HttpServletRequest request) {
		// 校验信息
		System.out.println("ddd"+request.getParameter("qq")+request.getParameter("weixin"));
//		if (request.getParameter("name").isEmpty()||request.getParameter("cellphone").isEmpty()||request.getParameter("qq").isEmpty()||request.getParameter("weixin").isEmpty()
//				||request.getParameter("email").isEmpty()			 ) {
//			System.out.println("bbb");	
//			return 4;
//		}
		System.out.println("ooo");
		Provider provider=new Provider();
		provider.setId(request.getParameter("id"));
		provider.setName(request.getParameter("name"));
		provider.setCellphone(request.getParameter("cellphone"));
		provider.setQq(request.getParameter("qq"));
		provider.setRegionId(request.getParameter("regionId"));
		provider.setWeixin(request.getParameter("weixin"));
		provider.setEmail(request.getParameter("email"));
		return providerMapper.updateByPrimaryKeySelective(provider);
	}

}
