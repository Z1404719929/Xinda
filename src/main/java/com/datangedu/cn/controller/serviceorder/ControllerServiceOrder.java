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
import com.datangedu.cn.servicegps.MemberService;
import com.datangedu.cn.servicegps.ProviderProductService;
import com.datangedu.cn.servicegps.ServiceOrderService;

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
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public Map<String, Object> getlist(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ServiceOrder> soList = soService.getso();
		List<ProviderProduct> ppList = ppService.getpp();
		
		// 查id
		List<Member> member;
		String[] name = new String[10];
		System.out.println(soList.size());
		for (int i = 0; i < soList.size(); i++) {
			System.out.println("用户id" + soList.get(i).getMemberId());
			member = mservice.getMember(soList.get(i).getMemberId());
			name[i] = member.get(0).getName();
			soList.get(i).setMemberId(member.get(0).getName());
			System.out.println("用户名" + soList.get(i).getMemberId());
			soList.get(i).setCellphone(member.get(0).getCellphone());
		}
//		map.put("name", name);

		// 查订单内容

		for (int j = 0; j < soList.size(); j++) {

			String[] str = soList.get(j).getServiceId().split(",");// str={0003*1,0002*2}
//			System.out.println(str[0]);
//			System.out.println(str.length);

			String[] str1 = new String[10];
			String[] str2 = new String[10];
			List<ProviderProduct> pp;
			StringBuffer buf = new StringBuffer();

			String str5 = "0001";
			for (int i = 0; i < str.length; i++) {
				String[] str3 = str[i].split("\\*"); // str3={0003,1}
				pp = ppService.getid(str3[0]); // 查询产品id
				str1[i] = pp.get(0).getServiceName(); // 产品名称存入str1
//				System.out.println(str1[i]);
				// 判断登录和当前需要展示的是否一样
				// List<ProviderProduct> ppList = ppservice.getid(pp.get(0).getProviderId());
				 System.out.println("5515"+pp.get(0).getProviderId().toString());
				// pp.get(0).getProviderId();
				 soList.get(i).setsName(pp.get(0).getServiceContent());

				str2[i] = str3[1]; // 数量存入str2
				 soList.get(i).setServiceNum(Integer.parseInt(str2[i]));
				System.out.println("结果" + str1[i] + "*" + str2[i]);
				/* buf.append(str1[i] + "*" + str2[i] + " "); */
				soList.get(j).setServiceId(buf.toString());
				System.out.println(buf.toString());
				
				if (pp.get(0).getProviderId().toString().equals(str5)) {
					soList.get(j).setLL(1);
					System.out.println("2222");
				}
				
			}
			System.out.println("zt"+soList.get(j).getLL());
		}

		/*
		 * // 状态 for (int i = 0; i < soList.size(); i++) { if (soList.get(i).getStatus()
		 * == 1) { soList.get(i).setZt("未支付"); } else { soList.get(i).setZt("已支付"); } }
		 */

//		System.out.println("map" + map);
		map.put("soList", soList);
		return map;
	}

}
