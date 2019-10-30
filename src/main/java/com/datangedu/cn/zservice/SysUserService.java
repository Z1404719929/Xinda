package com.datangedu.cn.zservice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.SysUser;

public interface SysUserService {
	public List<SysUser> setUserLogin(HttpServletRequest request);
	public int updatepassword(SysUser su,HttpServletRequest request);
}
