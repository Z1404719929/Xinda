package com.datangedu.cn.service.Implgps;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.ProviderProductMapper;
import com.datangedu.cn.model.sysUser.Provider;
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
	public String setProviderApa(HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		ProviderProduct providerProduct = new ProviderProduct();
		providerProduct.setId(uuid);
		providerProductMapper.insert(providerProduct);
		return uuid;
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
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		criteria.andIdEqualTo(request.getParameter("uuid"));

		System.out.println(Integer.valueOf(request.getParameter("price")));
		ProviderProduct providerProduct = new ProviderProduct();
		providerProduct.setServiceName(request.getParameter("serviceName"));
		providerProduct.setProviderId(request.getParameter("userid"));
		providerProduct.setStatus(1);
		providerProduct.setRecommend(2);
		providerProduct.setHighQuality(2);
		providerProduct.setSaleNum(0);
		java.sql.Date ctime = new java.sql.Date(new java.util.Date().getTime());
		providerProduct.setCreateTime(ctime);
		providerProduct.setProviderName(request.getParameter("sn"));
		providerProduct.setServiceContent(request.getParameter("serviceContent"));
		providerProduct.setPrice(Integer.valueOf(request.getParameter("price")));
//		providerProduct.setServiceImg(data);
		return providerProductMapper.updateByExampleSelective(providerProduct,providerProductExample);
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
	
	@Override
	public void saveServiceImg(ProviderProduct providerProduct) throws Exception{
			int i = providerProductMapper.saveServiceImg(providerProduct);
			System.out.println("i==="+i);
			if(i!=1) {
				throw new Exception("失败");
			} 
		}
	@Override
	public ProviderProduct getProviderInfoByld(String id) {
		return providerProductMapper.selectByPrimaryKey(id);
	}
}
