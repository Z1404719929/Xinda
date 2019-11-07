package com.datangedu.cn.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datangedu.cn.model.sysUser.Member;
import com.datangedu.cn.zservice.MemberService;



//登录
@Controller
@RequestMapping("/login")
public class ControllerMember4 {
	@Resource
	MemberService memberService;
	@ResponseBody
	@RequestMapping(value="/jdjd",method=RequestMethod.POST)
	public Map<String,Object> memberLogin(HttpServletRequest request) {
		System.out.println(8888888);
		Map<String,Object> map=new HashMap<String,Object>();
		String cellphone=request.getParameter("cellphone");
		String password=request.getParameter("password");
		String code = request.getParameter("code");
		System.out.println(cellphone);

		if(request.getParameter("cellphone").isEmpty()) {
		map.put("msg","手机号为空");
		return map;
		}
		System.out.println(12354);
		if(password.isEmpty()){
			map.put("msg","密码为空");
			return map;
		}
	 if(request.getParameter("code").isEmpty()){
		 map.put("msg","验证码为空"); 
		 return map; }

		HttpSession session = request.getSession();
		  System.out.println("验证码"+session.getAttribute("code"));
		  if(!session.getAttribute("code").equals(request.getParameter("code"))) {
		   map.put("msg","验证码错误" );
		   return map;
		  }
		
		List<Member> memberInfo=memberService.getMemberInfoById(request);
		System.out.println(memberInfo);
		
		if(memberInfo.isEmpty()) {
			map.put("msg","帐号不存在");
			return map;
		}else {
			map.put("code", 1);
			map.put("msg","恭喜登录成功");		
		}
		map.put("username", memberInfo.get(0).getName());
		map.put("userid", memberInfo.get(0).getId());
		return map;
	}

	
	//注册
	

	 public static long genItemId() {
	        //取当前时间的长整形值包含毫秒
	        long millis = System.currentTimeMillis();
	        //加上两位随机数
	        Random random = new Random();
	        int end2 = random.nextInt(99);
	        //如果不足两位前面补0
	        String str = millis + String.format("%02d", end2);
	        long id = new Long(str);
	        return id;
	    }
	
	/*
	 @ResponseBody
	 
	 @RequestMapping(value="/register/jghg",method=RequestMethod.POST) 
	 public Map<String,Object> memberRegister(HttpServletRequest request) {
	 Map<String,Object> map=new HashMap<String,Object>();
	  int memberInfo=memberService.setMemberRegister(request);
	  if(memberInfo==2) { 
	 map.put("msg","密码小于6位重新输入"); 
	 }
	 else {
	 map.put("msg","恭喜注册成功");
	  }
	  return map; 
	  }
	 */

/*
//购物车
@ResponseBody
@RequestMapping(value="/shopping",method=RequestMethod.POST)
public Map<String,Object> membercart(HttpServletRequest request) {

	Map<String,Object> map=new HashMap<String,Object>();
	int cartInfo=memberService.setCartnum(request);
	
	//String id=request.getParameter("service_id");
	map.put("msg","添加成功");
	map.put("code", 1);
	return map;
}
	
	*/


}
