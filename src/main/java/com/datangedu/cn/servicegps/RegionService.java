package com.datangedu.cn.servicegps;

import java.util.List;

import com.datangedu.cn.model.sysUser.Region;

public interface RegionService {

	List<Region> getList(String id);

	List<Region> getParent(String id);
	
	List<Region> getProvince(short id);

}