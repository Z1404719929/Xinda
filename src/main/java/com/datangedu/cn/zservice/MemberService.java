package com.datangedu.cn.zservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.Member;


public interface MemberService {
	public List<Member> getList();
	public List<Member> getMember(String id);
	public int register(HttpServletRequest request);
	List<Member> getcellphone(String id);
	public int updatepassword(Member member,HttpServletRequest request);
	public List<Member> getMemberInfoById(HttpServletRequest request);
}
