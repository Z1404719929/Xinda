package com.datangedu.cn.servicegps;

import java.util.List;

import com.datangedu.cn.model.sysUser.Member;

public interface MemberService {
	public List<Member> getList();

	public List<Member> getMember(String id);

}
