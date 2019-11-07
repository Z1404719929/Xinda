package com.datangedu.cn.service.Implgps;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.ProviderProductMapper;
import com.datangedu.cn.model.sysUser.ProviderExample;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.ProviderProductExample;
import com.datangedu.cn.model.sysUser.SysUserExample;
import com.datangedu.cn.servicegps.ProviderProductService2;

@Service
public class ProviderProductServiceImpl2 implements ProviderProductService2 {
	@Resource
	ProviderProductMapper providerProductMapper;

	public List<ProviderProduct> getProviderInfoByld() {
		System.out.println("查询用户");
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();

		return providerProductMapper.selectByExample(providerProductExample);
	}
	
	@Override
	public List<ProviderProduct> getid(String id) {
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		criteria.andIdEqualTo(id);
		return providerProductMapper.selectByExample(providerProductExample);
	}

	@Override
	public int setProviderRegister(HttpServletRequest request) {
		Integer price = null;
		if (request.getParameter("serviceName").isEmpty() || request.getParameter("serviceContent").isEmpty()
				|| request.getParameter("price").isEmpty()) {
			return 2;
		}
		// 判断输入的是数字还是字符串
		Pattern pattern = Pattern.compile("[0-9]*");
		if (!pattern.matcher(request.getParameter("price")).matches()) {
			return 2;
		}

		System.out.println(Integer.valueOf(request.getParameter("price")));
		ProviderProduct providerProduct = new ProviderProduct();
		providerProduct.setId(request.getParameter("id"));
		providerProduct.setServiceName(request.getParameter("serviceName"));
		providerProduct.setServiceContent(request.getParameter("serviceContent"));
		providerProduct.setPrice(Integer.valueOf(request.getParameter("price")));
		return providerProductMapper.insert(providerProduct);
	}
	
	@Override
	public int setProviderDelete(HttpServletRequest request) {

		String id = request.getParameter("id");
		System.out.println(id);
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		criteria.andIdEqualTo(id);
		return providerProductMapper.deleteByExample(providerProductExample);
	}
	
	@Override		//修改状态为2下线
	public int providerDown(ProviderProduct pp,String id) {
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		criteria.andIdEqualTo(id);
		pp.setStatus(2);
		return providerProductMapper.updateByExampleSelective(pp, providerProductExample);
	}

	@Override		//修改状态为1上线
	public int providerOline(ProviderProduct pp,String id) {
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		criteria.andIdEqualTo(id);
		pp.setStatus(1);
		return providerProductMapper.updateByExampleSelective(pp, providerProductExample);
	}
}
