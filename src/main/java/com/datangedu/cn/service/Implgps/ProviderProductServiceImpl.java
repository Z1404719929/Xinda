package com.datangedu.cn.service.Implgps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.ProviderProductMapper;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.ProviderProductExample;
import com.datangedu.cn.servicegps.ProviderProductService;

@Service
public class ProviderProductServiceImpl implements ProviderProductService {

	@Resource
	ProviderProductMapper providerProductMapper;

	// 查询所有产品
	@Override
	public List<ProviderProduct> getpp() {
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		System.out.println("查询结果几条===" + providerProductMapper.selectByExample(providerProductExample).size());
		return providerProductMapper.selectByExample(providerProductExample);
	}

	// 通过产品id查
	@Override
	public List<ProviderProduct> getid(String id) {
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		criteria.andIdEqualTo(id);
		return providerProductMapper.selectByExample(providerProductExample);
	}

}
