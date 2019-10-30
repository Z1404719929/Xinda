package com.datangedu.cn.zservicelmpl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.dao.mapper.MemberMapper;
import com.datangedu.cn.model.sysUser.Member;
import com.datangedu.cn.model.sysUser.MemberExample;
import com.datangedu.cn.zservice.MemberService;

@Service
public class MemberServicelmpl implements MemberService {
	
	@Resource
	MemberMapper memberMapper;
	
	@Override
	public List<Member> getList() {
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		return memberMapper.selectByExample(memberExample);
	}
	
	
	//通过id查
	@Override
	public List<Member> getMember(String id) {
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andIdEqualTo(id);
		return memberMapper.selectByExample(memberExample);
	}
	
	//注册插入用户
	public int register(HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		System.out.println("id=="+uuid);
		Member m=new Member();
		System.out.println("手机号"+request.getParameter("cellphone"));
		System.out.println("密码"+request.getParameter("password"));

		java.sql.Date ctime = new java.sql.Date(new java.util.Date().getTime());
		m.setRegisterTime(ctime);
		m.setId(uuid);
		m.setName(request.getParameter("cellphone"));
		m.setCellphone(request.getParameter("cellphone"));
		m.setPassword(MD5Util.getMD5(request.getParameter("password").getBytes()));
		m.setStatus(1);
		m.setBuyNum(0);
		m.setTotalPrice(0);
		m.setRegionId(request.getParameter("qu"));
		return memberMapper.insert(m);
	}
	
	
	//通过手机号查
		@Override
		public List<Member> getcellphone(String id) {
			MemberExample memberExample = new MemberExample();
			MemberExample.Criteria criteria = memberExample.createCriteria();
			criteria.andCellphoneEqualTo(id);
			return memberMapper.selectByExample(memberExample);
		}

		
		//修改密码
		public int updatepassword(Member member,HttpServletRequest request) {
			MemberExample memberExample = new MemberExample();
			MemberExample.Criteria criteria = memberExample.createCriteria();
			criteria.andCellphoneEqualTo(request.getParameter("cellphone"));
			return memberMapper.updateByExampleSelective(member, memberExample);
		}
}