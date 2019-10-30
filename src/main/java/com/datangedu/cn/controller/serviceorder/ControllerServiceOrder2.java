package com.datangedu.cn.controller.serviceorder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datangedu.cn.model.sysUser.Member;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.ServiceOrder;
import com.datangedu.cn.zservice.MemberService;
import com.datangedu.cn.zservice.ProviderProductService;
import com.datangedu.cn.zservice.ServiceOrderService;

//运营订单页面
@Controller
@RequestMapping("/oo1")
public class ControllerServiceOrder2 {
	@Resource
	ServiceOrderService soService;
	@Resource
	MemberService mservice;
	@Resource
	ProviderProductService ppservice;
	
	@ResponseBody
	@RequestMapping(value="/getlist1",method = RequestMethod.GET)
	public Map<String,Object> getlist(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		List<ServiceOrder> soList= soService.getso();
		map.put("soList", soList);
		//查id
		List<Member> member;
		String [] name=new String[10];
		System.out.println(soList.size());
		for(int i=0;i<soList.size();i++) {
			System.out.println("用户id"+soList.get(i).getMemberId());
			member=mservice.getMember(soList.get(i).getMemberId());
			name[i]=member.get(0).getName();
			soList.get(i).setMemberId(member.get(0).getName());
			System.out.println("用户名"+soList.get(i).getMemberId());
		}
//		map.put("name", name);
		
		
		
		//查订单内容
		
		for(int j=0;j<soList.size();j++) {

		String [] str=soList.get(j).getServiceId().split(",");//str={0003*1,0002*2}
		System.out.println(str[0]);
		System.out.println(str.length);
		
		String [] str1=new String[10];
		String [] str2=new String[10];
		List<ProviderProduct> pp;
		StringBuffer buf=new StringBuffer();
		
		//“0002”
		
		for(int i=0;i<str.length;i++) {
			String [] str3=str[i].split("\\*");	//str3={0003,1}
			
			pp=ppservice.getid(str3[0]);		//查询产品id
			str1[i]=pp.get(0).getServiceName();		//产品名称存入str1
			
//			System.out.println(pp.get(0).getProviderId());
//			List<ProviderProduct> ppList= ppservice.getid(str1[i]);
//			ppList.get(0).getProviderId();
//			if(ppList.get(0).getProviderId()=="0001"){
//			soList.get(j).setZt("1");
//			}else{
//			soList.get(j).setZt("2");
//			}
			str2[i]=str3[1];											//数量存入str2
			System.out.println("结果"+str1[i]+"*"+str2[i]);
			buf.append(str1[i]+"*"+str2[i]+" ");
			soList.get(j).setServiceId(buf.toString());
			System.out.println(buf.toString());
		}
		
		}
		
		//状态
		for(int i=0;i<soList.size();i++) {
			if(soList.get(i).getStatus()==1) {
			soList.get(i).setZt("未支付");
		}else {
			soList.get(i).setZt("已支付");
		}
		}
		
		System.out.println("map"+map);
		return map;
}

}
