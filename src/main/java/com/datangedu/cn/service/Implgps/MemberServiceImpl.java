package com.datangedu.cn.service.Implgps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.MemberMapper;
import com.datangedu.cn.model.sysUser.Member;
import com.datangedu.cn.model.sysUser.MemberExample;
import com.datangedu.cn.servicegps.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	@Resource
	MemberMapper memberMapper;

	@Override
	public List<Member> getList() {
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		return memberMapper.selectByExample(memberExample);
	}

	// 通过id查
	@Override
	public List<Member> getMember(String id) {
		MemberExample memberExample = new MemberExample();
		MemberExample.Criteria criteria = memberExample.createCriteria();
		criteria.andIdEqualTo(id);
		return memberMapper.selectByExample(memberExample);
	}

}
