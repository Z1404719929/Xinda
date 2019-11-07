package com.datangedu.cn.zservicelmpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.CartMapper;
import com.datangedu.cn.dao.mapper.ProviderProductMapper;
import com.datangedu.cn.model.sysUser.Cart;
import com.datangedu.cn.model.sysUser.CartExample;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.ProviderProductExample;
import com.datangedu.cn.model.sysUser.SysUserExample;
import com.datangedu.cn.zservice.ProviderProductService;

@Service
public class ProviderProductServicelmpl implements ProviderProductService {


	@Resource
	ProviderProductMapper providerProductMapper;

	//查询所有产品
	@Override
	public List<ProviderProduct> getpp() {
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		System.out.println("查询结果几条==="+providerProductMapper.selectByExample(providerProductExample).size());
		return providerProductMapper.selectByExample(providerProductExample);
	}
	
	//通过产品id查
	@Override
	public List<ProviderProduct> getid(String id) {
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		criteria.andIdEqualTo(id);
		return providerProductMapper.selectByExample(providerProductExample);
	}
	
	@Override		//修改状态为2下线
	public int xx(ProviderProduct pp,String id) {
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		criteria.andIdEqualTo(id);
		pp.setStatus(2);
		return providerProductMapper.updateByExampleSelective(pp, providerProductExample);
	}

	@Override		//修改状态为1上线
	public int sx(ProviderProduct pp,String id) {
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		criteria.andIdEqualTo(id);
		pp.setStatus(1);
		return providerProductMapper.updateByExampleSelective(pp, providerProductExample);
	}

	
	//查询所有商品
	@Override
	public List<ProviderProduct> getProviderProductInfoById() {
		// TODO Auto-generated method stub
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		System.out.println("查询结果几条==="+providerProductMapper.selectByExample(providerProductExample).size());
		return providerProductMapper.selectByExample(providerProductExample);
	}

	

	
	
	
}
