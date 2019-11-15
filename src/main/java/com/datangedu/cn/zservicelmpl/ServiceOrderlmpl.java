package com.datangedu.cn.zservicelmpl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.ServiceOrderMapper;
import com.datangedu.cn.model.sysUser.Cart;
import com.datangedu.cn.model.sysUser.CartExample;
import com.datangedu.cn.model.sysUser.Member;
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
	


	@Override//费用中心查询
	public List<ServiceOrder> getpay() {
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		serviceOrderExample.setStatus(2);
		return ServiceOrderMapper.selectByLike2(serviceOrderExample);
	}
	
	
	//通过用户userid查
	@Override
	public List<ServiceOrder> getuid(String userid) {	
		ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
		ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
		criteria.andMemberIdEqualTo(userid);
		return ServiceOrderMapper.selectByExample(serviceOrderExample);
	}
	
	
	
	
	
	
	
	
	//通过用户userid查
		@Override
		public List<ServiceOrder> getll(String userid) {	
			ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
			ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
			criteria.andMemberIdEqualTo(userid);
//			criteria.andStatusEqualTo(2);
			return ServiceOrderMapper.selectByExample(serviceOrderExample);
		}

		

	//将订单信息插入serviceorder表
	@Override
	public int insertServiceOrder(HttpServletRequest request) {
		// TODO Auto-generated method stub

	   	ServiceOrder serviceOrder=new ServiceOrder();
	   	System.out.println("插入成功");
	   	serviceOrder.setServiceNo(request.getParameter("nn"));
	   	serviceOrder.setMemberId(request.getParameter("userid"));
	   	serviceOrder.setServiceId(request.getParameter("str4")); 	
	   	serviceOrder.setTotalPrice(Integer.valueOf(request.getParameter("totalprice1")));
	   	serviceOrder.setStatus(1);
		serviceOrder.setPayType(0);
		java.sql.Date ctime = new java.sql.Date(new java.util.Date().getTime()); 
	   	serviceOrder.setCreateTime(ctime);
		return ServiceOrderMapper.insert(serviceOrder);
	}
	
	
	//修改支付类型
	public int up(HttpServletRequest request){
		ServiceOrder serviceOrder=new ServiceOrder();
		serviceOrder.setServiceNo(request.getParameter("nn"));
		serviceOrder.setPayType(Integer.valueOf(request.getParameter("paytype")));
		serviceOrder.setStatus(2);
		int so=ServiceOrderMapper.updateByPrimaryKeySelective(serviceOrder);
		return so;
	}
	
	
	
	//删除订单
	@Override
	public int pProductDelete(HttpServletRequest request) {

			// TODO Auto-generated method stub
			String serviceNo=request.getParameter("serviceNo");
			System.out.println(1111111111+serviceNo);
			ServiceOrderExample serviceOrderExample=new ServiceOrderExample();
			ServiceOrderExample.Criteria criteria=serviceOrderExample.createCriteria();
			criteria.andServiceNoEqualTo(serviceNo);
			return ServiceOrderMapper.deleteByExample(serviceOrderExample);
	}	
	
	//修改订单支付
	@Override
	public int pProductup(HttpServletRequest request) {
		return 0;
		
	}

	
	//通过订单serciceNo查
			@Override
			public List<ServiceOrder> getmm(String serciceNo) {	
				ServiceOrderExample serviceOrderExample = new ServiceOrderExample();
				ServiceOrderExample.Criteria criteria = serviceOrderExample.createCriteria();
				criteria.andServiceNoEqualTo(serciceNo);
//				criteria.andStatusEqualTo(2);
				return ServiceOrderMapper.selectByExample(serviceOrderExample);
			}

	
}
