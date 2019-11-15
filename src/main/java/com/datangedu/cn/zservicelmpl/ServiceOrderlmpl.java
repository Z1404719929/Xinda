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
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.IntArraySerializer;

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
	public List<ServiceOrder> getpaylist(HttpServletRequest request) {
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		serviceOrderExample.setStatus(2);
		return ServiceOrderMapper.selectByLike2(serviceOrderExample);
	}
	
	@Override//费用中心分页查询
	public List<ServiceOrder> getpay(HttpServletRequest request) {
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		serviceOrderExample.setStatus(2);
		serviceOrderExample.setPageSize((Integer.valueOf(request.getParameter("pageSize"))-1)*2);
		System.out.println("getPageSize"+serviceOrderExample.getPageSize());
		serviceOrderExample.setPageNum(Integer.valueOf(request.getParameter("pageNum")));
		System.out.println("getPageNum"+serviceOrderExample.getPageNum());
		return ServiceOrderMapper.selectBypaging(serviceOrderExample);
	}
	
	@Override//费用中心分页查询
	public List<ServiceOrder> gettime(HttpServletRequest request) {
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		serviceOrderExample.setStatus(2);
		serviceOrderExample.setTime(Integer.valueOf(request.getParameter("time")));
		return ServiceOrderMapper.selectBytime(serviceOrderExample);
	}
	
	@Override//费用中心分页查询
	public List<ServiceOrder> gettimepage(HttpServletRequest request) {
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		serviceOrderExample.setStatus(2);
		serviceOrderExample.setTime(Integer.valueOf(request.getParameter("time")));
		serviceOrderExample.setPageSize((Integer.valueOf(request.getParameter("pageSize"))-1)*2);
		System.out.println("getPageSize"+serviceOrderExample.getPageSize());
		serviceOrderExample.setPageNum(Integer.valueOf(request.getParameter("pageNum")));
		System.out.println("getPageNum"+serviceOrderExample.getPageNum());
		return ServiceOrderMapper.selectBytimepage(serviceOrderExample);
	}
	
	///////////////////////////////////////////////////////////////////////////服务商订单
	@Override
	public List<ServiceOrder> getlist(HttpServletRequest request) {
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		serviceOrderExample.setStatus(Integer.valueOf(request.getParameter("status")));
		System.out.println("serviceOrderExample"+serviceOrderExample.getStatus());
		return ServiceOrderMapper.selectByorderform(serviceOrderExample);
	}

	@Override
	public List<ServiceOrder> getlist2(HttpServletRequest request) {
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		serviceOrderExample.setStatus(2);
		serviceOrderExample.setPageSize((Integer.valueOf(request.getParameter("pageSize"))-1)*2);
		System.out.println("getPageSize"+serviceOrderExample.getPageSize());
		serviceOrderExample.setPageNum(Integer.valueOf(request.getParameter("pageNum")));
		System.out.println("getPageNum"+serviceOrderExample.getPageNum());
		return ServiceOrderMapper.selectByorderformpage(serviceOrderExample);
	}

	//////////////////////////////////////////////用户id查订单
	@Override
	public List<ServiceOrder> content(HttpServletRequest request) {
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		criteria.andMemberIdEqualTo(request.getParameter("userid"));
		return ServiceOrderMapper.selectByExample(serviceOrderExample);
	}
	
}
