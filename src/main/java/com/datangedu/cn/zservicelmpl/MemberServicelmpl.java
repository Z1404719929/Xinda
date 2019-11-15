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
import com.datangedu.cn.model.sysUser.SysUserExample;
import com.datangedu.cn.model.sysUser.ServiceOrder;
import com.datangedu.cn.zservice.MemberService;

@Service
public class MemberServicelmpl implements MemberService {
	
	@Resource
	MemberMapper memberMapper;
	
	public List<Member> getMemberInfoById(HttpServletRequest request){
		System.out.println("查询前");
		System.out.println("输入的手机号=="+request.getParameter("cellphone"));
		System.out.println("输入的密码=="+request.getParameter("password"));
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andCellphoneEqualTo(request.getParameter("cellphone"));
		criteria.andPasswordEqualTo(request.getParameter("password"));
		System.out.println("查询结束");
		return memberMapper.selectByExample(memberExample);
	}
	
	@Override
	public List<Member> getList() {
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		return memberMapper.selectByExample(memberExample);
	}
	
	
	//模糊查询
			@Override
			public List<Member> select(HttpServletRequest request) {
				MemberExample memberExample = new MemberExample();
				MemberExample.Criteria criteria = memberExample.createCriteria();
				memberExample.setLikeName(request.getParameter("name"));
				return memberMapper.selectByLike(memberExample);
			}
	
	//模糊查询分页
		@Override
		public List<Member> selectpaging(HttpServletRequest request) {
			MemberExample memberExample = new MemberExample();
			MemberExample.Criteria criteria = memberExample.createCriteria();
			memberExample.setLikeName(request.getParameter("name"));
			memberExample.setPageSize((Integer.valueOf(request.getParameter("pageSize"))-1)*2);
			System.out.println("getPageSize"+memberExample.getPageSize());
			memberExample.setPageNum(Integer.valueOf(request.getParameter("pageNum")));
			System.out.println("getPageNum"+memberExample.getPageNum());
			return memberMapper.selectBypaging(memberExample);
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
	@Override
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
		@Override
		public int updatepassword(Member member,HttpServletRequest request) {
			MemberExample memberExample = new MemberExample();
			MemberExample.Criteria criteria = memberExample.createCriteria();
			criteria.andCellphoneEqualTo(request.getParameter("cellphone"));
			return memberMapper.updateByExampleSelective(member, memberExample);
		}
		
		//////////////////////////显示图片用
		@Override
		public Member getUserInfo(String id) {
			return memberMapper.selectByPrimaryKey(id);
		}
		///////////////////////////////////////修改账户信息
		@Override
		public int updatexx(HttpServletRequest request) {
			Member member=new Member();
			member.setId(request.getParameter("userid"));
			member.setName(request.getParameter("name"));
			member.setEmail(request.getParameter("email"));
			member.setGender(Integer.valueOf(request.getParameter("sex")));
			return memberMapper.updateByPrimaryKeySelective(member);
		}
		
		/////////////////////////////////保存头像
		@Override
		public void saveUserImg(Member member) throws Exception {
			int i = memberMapper.saveUserImg(member);
			if(i!=1) {
				throw new Exception("更新用户头像失败");
			}
		}

		
}
