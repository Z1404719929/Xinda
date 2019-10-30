package com.datangedu.cn.zservicelmpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.SysUserMapper;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.ProviderProductExample;
import com.datangedu.cn.model.sysUser.SysUser;
import com.datangedu.cn.model.sysUser.SysUserExample;
import com.datangedu.cn.zservice.SysUserService;


@Service
public class SysUserServicelmpl implements SysUserService {

	@Resource
	SysUserMapper sysUserMapper;
	
	//登录
	@Override
	public List<SysUser> setUserLogin(HttpServletRequest request) {
		System.out.println("查询前");
		System.out.println("输入的手机号=="+request.getParameter("cellphone"));
		System.out.println("输入的密码=="+request.getParameter("password"));
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andCellphoneEqualTo(request.getParameter("cellphone"));
		criteria.andPasswordEqualTo(request.getParameter("password"));
		System.out.println("查询结束");
		return sysUserMapper.selectByExample(sysUserExample);
	}
	
	//修改密码
	public int updatepassword(SysUser su,HttpServletRequest request) {
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		criteria.andCellphoneEqualTo(request.getParameter("cellphone"));
		return sysUserMapper.updateByExampleSelective(su, sysUserExample);
	}

}
