package com.datangedu.cn.controller.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datangedu.cn.model.sysUser.Provider;
import com.datangedu.cn.model.sysUser.Region;
import com.datangedu.cn.zservice.ProviderService;
import com.datangedu.cn.zservice.RegionService;


@Controller
@RequestMapping("/p")
public class ControllerProvider5 {
	
	@Resource
	ProviderService pService;
	@Resource
	RegionService rService;
	
	//服务商列表分页
			@ResponseBody
			@RequestMapping(value="/paging",method = RequestMethod.POST)
			public Map <String,Object> select(HttpServletRequest request) {
				Map<String,Object> map = new HashMap<String,Object>();
				System.out.println("传入pageSize=="+request.getParameter("pageSize"));
				System.out.println("传入pageNum=="+request.getParameter("pageNum"));
				List<Provider> allList= pService.select(request);	//得到查询的所有数量
				int pageNum=(allList.size()+1)/2;		//计算页数
				map.put("pageNum", pageNum);
				List<Provider> mList= pService.selectpaging(request);
				System.out.println();
				System.out.println("mList.size()"+mList.size());
				map.put("List", mList);

				String [] str = new String[3];
				for(int i=0;i<mList.size();i++) {
				String str8="";
				List<Region> rList= rService.getList(mList.get(i).getRegionId());
				str[2]=rList.get(0).getName();
				
				if(rList.get(0).getLevel()==3) {
				rList= rService.getList(rList.get(0).getParentId());
				str[1]=rList.get(0).getName();
				}
				
				if(rList.get(0).getLevel()==2) {
					rList= rService.getList(rList.get(0).getParentId());
					str[0]=rList.get(0).getName();
				}
				str8=str[0]+" "+str[1]+" "+str[2];
				str[0]="";str[1]="";str[2]="";
				mList.get(i).setRegionId(str8);
				}
				System.out.println("hhhhh");
				return map;
		}
			
			@ResponseBody
			@RequestMapping(value="/startstop",method = RequestMethod.POST)
			public Map <String,Object> startstop(HttpServletRequest request) {
				Map<String,Object> map = new HashMap<String,Object>();
				int a= pService.starstop(request);	//得到查询的所有数量
				return map;
			}
}
