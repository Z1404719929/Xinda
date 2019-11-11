package com.datangedu.cn.controller.providerproduct;

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
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.zservice.ProviderProductService;

@Controller
@RequestMapping("/pp")
public class ControllerProviderProduct2 {

	@Resource
	ProviderProductService ppService;
//	SysUserService suService;
	
	@ResponseBody			//查询所有产品
	@RequestMapping(value="/getlist",method = RequestMethod.GET)
	public Map <String,Object> getlist(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<ProviderProduct> ppList= ppService.getpp();
		System.out.println(ppList); 
		map.put("ppList", ppList);
		System.out.println("map"+map);
		return map;
	}
	
	@ResponseBody			//模糊查询
	@RequestMapping(value="/select",method = RequestMethod.POST)
	public Map <String,Object> selecte(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<ProviderProduct> ppList= ppService.select(request);
		System.out.println(ppList); 
		map.put("ppList", ppList);
		return map;
	}
	
	
	
	@ResponseBody			//单个产品上下线
	@RequestMapping(value="/us",method = RequestMethod.POST)
	public Map <String,Object> xx(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<ProviderProduct> ppList= ppService.getid(request.getParameter("id"));
		System.out.println("pp"+ppList.get(0).getStatus());
		if(ppList.get(0).getStatus()==1) {
			ProviderProduct pp=new ProviderProduct();
			pp.setId(ppList.get(0).getId());
			int a=ppService.xx(pp, request.getParameter("id"));
		}else {
			ProviderProduct pp=new ProviderProduct();
			pp.setId(ppList.get(0).getId());
			int a=ppService.sx(pp, request.getParameter("id"));
		}
		return map;
	}
	
	@ResponseBody			//所有产品上线
	@RequestMapping(value="/allUpLine",method = RequestMethod.POST)
	public Map <String,Object> allUpLine(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		String [] str=request.getParameter("str").split(",");
		System.out.println("str=="+str.length);
		for(int i=0;i<str.length;i++) {
			List<ProviderProduct> ppList= ppService.getid(str[i]);
			if(ppList.get(0).getStatus()==2) {
				ProviderProduct pp=new ProviderProduct();
				pp.setId(ppList.get(0).getId());
				ppService.sx(pp, str[i]);
			}
		}
		return map;
	}
	
	@ResponseBody				//所有产品下线
	@RequestMapping(value="/allDownLine",method = RequestMethod.POST)
	public Map <String,Object> allDownLine(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		String [] str=request.getParameter("str").split(",");
		System.out.println("str=="+str.length);
		for(int i=0;i<str.length;i++) {
			List<ProviderProduct> ppList= ppService.getid(str[i]);
			if(ppList.get(0).getStatus()==1) {
				ProviderProduct pp=new ProviderProduct();
				pp.setId(ppList.get(0).getId());
				ppService.xx(pp, str[i]);
			}
		}
		return map;
	}
	
	@ResponseBody				//所有产品下线
	@RequestMapping(value="/del",method = RequestMethod.POST)
	public Map <String,Object> del(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		ppService.del(request);
		return map;
	}
	
	//////////////////////////////////////////////////////////////////
	@ResponseBody	
	@RequestMapping(value="/recommend",method = RequestMethod.POST)
	public Map <String,Object> paging(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println("传入pageSize=="+request.getParameter("pageSize"));
		System.out.println("传入pageNum=="+request.getParameter("pageNum"));
		List<ProviderProduct> allList= ppService.selectAll(request);	//得到查询的所有数量
		int pageNum=(allList.size()+1)/2;		//计算页数
		map.put("pageNum", pageNum);
		List<ProviderProduct> mList= ppService.selectpaging(request);
		System.out.println();
		System.out.println("mList.size()"+mList.size());
		map.put("List", mList);
		return map;
	}
	
	//是否推荐
	@ResponseBody	
	@RequestMapping(value="/check1",method = RequestMethod.POST)
	public Map <String,Object> check1(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		ppService.check1(request);
		return map;
	}
	//是否必备
		@ResponseBody	
		@RequestMapping(value="/check2",method = RequestMethod.POST)
		public Map <String,Object> check2(HttpServletRequest request) {
			Map<String,Object> map = new HashMap<String,Object>();
			ppService.check2(request);
			return map;
		}
	
}
