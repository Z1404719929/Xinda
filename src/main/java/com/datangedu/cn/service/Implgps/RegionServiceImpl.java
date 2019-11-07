package com.datangedu.cn.service.Implgps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.RegionMapper;
import com.datangedu.cn.model.sysUser.Region;
import com.datangedu.cn.model.sysUser.RegionExample;
import com.datangedu.cn.servicegps.RegionService;
@Service
public class RegionServiceImpl implements RegionService {

	@Resource
	RegionMapper regionMapper;
	
	//查id相等的
	@Override
	public List<Region> getList(String id) {
		RegionExample regionExample = new RegionExample();
		RegionExample.Criteria criteria = regionExample.createCriteria();
		criteria.andIdEqualTo(id);
		return regionMapper.selectByExample(regionExample);
	}	
	
	//查上一级
	@Override
	public List<Region> getParent(String id) {
		RegionExample regionExample = new RegionExample();
		RegionExample.Criteria criteria = regionExample.createCriteria();
		criteria.andParentIdEqualTo(id);
		return regionMapper.selectByExample(regionExample);
	}
	
	//查省市区
	public List<Region> getProvince(short id) {
		RegionExample regionExample = new RegionExample();
		RegionExample.Criteria criteria = regionExample.createCriteria();
		criteria.andLevelEqualTo(id);
		return regionMapper.selectByExample(regionExample);
	}
	
}
