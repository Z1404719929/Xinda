package com.datangedu.cn.service.Implgps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.ServiceOrderMapper;
import com.datangedu.cn.model.sysUser.ServiceOrder;
import com.datangedu.cn.model.sysUser.ServiceOrderExample;
import com.datangedu.cn.servicegps.ServiceOrderService;

@Service
public class ServiceOrderServiceImpl implements ServiceOrderService {
	@Resource
	ServiceOrderMapper ServiceOrderMapper;
	@Override // 查询服务订单
	public List<ServiceOrder> getso() {
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		// System.out.println("查询结果几条==="+ServiceOrderMapper.selectByExample(serviceOrderExample).size());
		return ServiceOrderMapper.selectByExample(serviceOrderExample);
	}
}
