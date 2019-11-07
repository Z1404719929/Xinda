package com.datangedu.cn.controller.member;

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
import com.datangedu.cn.model.sysUser.Region;
import com.datangedu.cn.zservice.MemberService;
import com.datangedu.cn.zservice.RegionService;

@Controller
@RequestMapping("/ou")
public class ControllerMember2 {
	@Resource
	MemberService mService;
	@Resource
	RegionService rService;

	// 运营管理界面显示注册用户列表
	@ResponseBody
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public Map<String, Object> getlist(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Member> mList = mService.getList();
		int pageNum = (mList.size() + 1) / 2; // 计算页数
		map.put("pageNum", pageNum);
//		System.out.println(pageNum);

		map.put("mList", mList);

		String[] str = new String[3];
		for (int i = 0; i < mList.size(); i++) {
			String str8 = "";
			List<Region> rList = rService.getList(mList.get(i).getRegionId());
			str[2] = rList.get(0).getName();
//		System.out.println(str[2]);

			if (rList.get(0).getLevel() == 3) {
				rList = rService.getList(rList.get(0).getParentId());
				str[1] = rList.get(0).getName();
//		System.out.println(str[1]);
			}

			if (rList.get(0).getLevel() == 2) {
				rList = rService.getList(rList.get(0).getParentId());
				str[0] = rList.get(0).getName();
//			System.out.println(str[0]);
			}
			str8 = str[0] + " " + str[1] + " " + str[2];
			str[0] = "";
			str[1] = "";
			str[2] = "";
			System.out.println(str8);
			mList.get(i).setRegionId(str8);
//		System.out.println(rList.get(0).getId());
		}

		return map;
	}

	// 注册用户列表分页
	@ResponseBody
	@RequestMapping(value = "/paging", method = RequestMethod.POST)
	public Map<String, Object> select(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("传入pageSize==" + request.getParameter("pageSize"));
		System.out.println("传入pageNum==" + request.getParameter("pageNum"));
		List<Member> allList = mService.select(request); 	// 得到查询的所有数量
		int pageNum = (allList.size() + 1) / 2; 						// 计算页数
		map.put("pageNum", pageNum);
		List<Member> mList = mService.selectpaging(request);
		System.out.println();
		System.out.println("mList.size()" + mList.size());
		map.put("mList", mList);

		String[] str = new String[3];
		for (int i = 0; i < mList.size(); i++) {
			String str8 = "";
			List<Region> rList = rService.getList(mList.get(i).getRegionId());
			str[2] = rList.get(0).getName();
//			System.out.println(str[2]);

			if (rList.get(0).getLevel() == 3) {
				rList = rService.getList(rList.get(0).getParentId());
				str[1] = rList.get(0).getName();
//			System.out.println(str[1]);
			}

			if (rList.get(0).getLevel() == 2) {
				rList = rService.getList(rList.get(0).getParentId());
				str[0] = rList.get(0).getName();
//				System.out.println(str[0]);
			}
			str8 = str[0] + " " + str[1] + " " + str[2];
			str[0] = "";
			str[1] = "";
			str[2] = "";
//			System.out.println(str8);
			mList.get(i).setRegionId(str8);
//			System.out.println(rList.get(0).getId());
		}

		return map;
	}


}
