package com.datangedu.cn.zservicelmpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.ServiceOrderMapper;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.ServiceOrder;
import com.datangedu.cn.model.sysUser.ServiceOrderExample;
import com.datangedu.cn.zservice.ServiceOrderService;

@Service
public class ServiceOrderlmpl implements ServiceOrderService {
	@Resource
	ServiceOrderMapper ServiceOrderMapper;
	
	@Override//查询服务订单
	public List<ServiceOrder> getso() {
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		//System.out.println("查询结果几条==="+ServiceOrderMapper.selectByExample(serviceOrderExample).size());
		return ServiceOrderMapper.selectByExample(serviceOrderExample);
	}
	
	//模糊查询
	public List<ServiceOrder> select(HttpServletRequest request){
		List<ServiceOrder> so=ServiceOrderMapper.selectByLike(request.getParameter("name"));
		return so;
	}
	
	/////////////////////////////////////////////////
	@Override//费用中心查询
	public List<ServiceOrder> getpay() {
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		serviceOrderExample.setStatus(2);
		return ServiceOrderMapper.selectByLike2(serviceOrderExample);
	}

}
