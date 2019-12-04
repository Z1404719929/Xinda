package com.datangedu.cn.zservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.Member;
import com.datangedu.cn.model.sysUser.Provider;

public interface ProviderService {
	public List<Provider> getList();

	List<Provider> select(HttpServletRequest request);

	List<Provider> selectpaging(HttpServletRequest request);

	List<Provider> getList2();

	int starstop(HttpServletRequest request);

	List<Provider> getid(HttpServletRequest request);

}
