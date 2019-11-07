package com.datangedu.cn.zservicelmpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.ProviderMapper;
import com.datangedu.cn.model.sysUser.Member;
import com.datangedu.cn.model.sysUser.MemberExample;
import com.datangedu.cn.model.sysUser.Provider;
import com.datangedu.cn.model.sysUser.ProviderExample;
import com.datangedu.cn.zservice.ProviderService;
@Service
public class ProviderServicelmpl implements ProviderService {

	@Resource
	ProviderMapper providerMapper;
	
	//查询状态是正常的
	@Override
	public List<Provider> getList() {
		ProviderExample providerExample = new ProviderExample();
		ProviderExample.Criteria criteria = providerExample.createCriteria();
		criteria.andStatusEqualTo(1);
		return providerMapper.selectByExample(providerExample);
	}
	
	//查询状态是正常的
		@Override
		public List<Provider> getList2() {
			ProviderExample providerExample = new ProviderExample();
			ProviderExample.Criteria criteria = providerExample.createCriteria();
			criteria.andStatusEqualTo(2);
			return providerMapper.selectByExample(providerExample);
		}
	
	//模糊查询
	@Override
	public List<Provider> select(HttpServletRequest request) {
		ProviderExample providerExample = new ProviderExample();
		ProviderExample.Criteria criteria = providerExample.createCriteria();
		providerExample.setLikeName(request.getParameter("name"));
		providerExample.setStatus(Integer.valueOf(request.getParameter("index")));
		return providerMapper.selectByLike(providerExample);
	}
	
			//模糊查询分页
			@Override
			public List<Provider> selectpaging(HttpServletRequest request) {
				ProviderExample providerExample = new ProviderExample();
				ProviderExample.Criteria criteria = providerExample.createCriteria();
				providerExample.setStatus(Integer.valueOf(request.getParameter("index")));
				providerExample.setLikeName(request.getParameter("name"));
				providerExample.setPageSize((Integer.valueOf(request.getParameter("pageSize"))-1)*2);
				System.out.println("getPageSize"+providerExample.getPageSize());
				providerExample.setPageNum(Integer.valueOf(request.getParameter("pageNum")));
				System.out.println("getPageNum"+providerExample.getPageNum());
				return providerMapper.selectBypaging(providerExample);
			}

}
