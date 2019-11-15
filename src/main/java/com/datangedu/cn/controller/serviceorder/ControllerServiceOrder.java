package com.datangedu.cn.controller.serviceorder;

import java.util.ArrayList;
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

@Controller
@RequestMapping("/oo")
public class ControllerServiceOrder {
	@Resource
	ServiceOrderService soService;
	@Resource
	MemberService mservice;
	@Resource
	ProviderProductService ppService;

	@ResponseBody
	@RequestMapping(value = "/getlist", method = RequestMethod.POST)
	public Map<String, Object> getlist(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
//		List<ServiceOrder> allList = soService.getlist(request);
//		int pageNum=(allList.size()+1)/2;		//计算页数
//		map.put("pageNum", pageNum);
//		System.out.println("分页=="+pageNum);
		List<ServiceOrder> soList = soService.getlist(request);
		System.out.println("soList长度=="+soList.size());
		
		List<ServiceOrder> setList = new ArrayList<ServiceOrder>();
		List<Member> member;
		List<ProviderProduct> ppList= ppService.select(request);		//模糊查询查到所有产品id
		System.out.println("模糊查询=="+ppList.size());
		for(int i=0;i<soList.size();i++) {		//
			System.out.println("4444444444444444444444444444444444444");
			String name = "";
				System.out.println("用户id" + soList.get(i).getMemberId());
				member = mservice.getMember(soList.get(i).getMemberId());
				name= member.get(0).getName();
				soList.get(i).setMemberId(member.get(0).getName());
				System.out.println("用户名" + soList.get(i).getMemberId());
				soList.get(i).setCellphone(member.get(0).getCellphone());
				
				String[] str = soList.get(i).getServiceId().split(",");	//00003*2 00004*3
				for(int j=0;j<str.length;j++) {
					String[] str2 = str[j].split("\\*"); // str2={00003,2} 00004,3
					List<ProviderProduct> pp = ppService.getid(str2[0]); // 查询00003产品信息
					System.out.println("str2[0]"+str2[0]);
					for(int n=0;n<ppList.size();n++) {
					if(str2[0].equals(ppList.get(n).getId())&&pp.get(0).getProviderId().equals(request.getParameter("userid"))) {
						ServiceOrder so=new ServiceOrder();
						so.setServiceNo(soList.get(i).getServiceNo());
						so.setServiceId(pp.get(0).getServiceContent());
						System.out.println("yongh=="+so.getServiceId());
						so.setMemberId(soList.get(i).getMemberId());
						so.setCellphone(member.get(0).getCellphone());
						so.setServiceNum(Integer.valueOf(str2[1]));
						so.setTotalPrice(pp.get(0).getPrice()*Integer.valueOf(str2[1]));
						so.setCreateTime(soList.get(i).getCreateTime());
						setList.add(so);
					}
					}
				}
		}
		
		for(int i=0;i<setList.size();i++) {
			System.out.println("hhhh"+setList.get(i).getServiceNo());
			System.out.println("service==="+setList.get(i).getServiceId());
		}
		
		//计算页数
		int pageNum=(setList.size()+1)/2;	
		map.put("pageNum", pageNum);
		System.out.println("分页=="+pageNum);

		map.put("soList", setList);
		return map;
	}


}

