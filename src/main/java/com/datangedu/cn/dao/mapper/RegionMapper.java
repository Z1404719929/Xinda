package com.datangedu.cn.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.datangedu.cn.model.sysUser.Region;
import com.datangedu.cn.model.sysUser.RegionExample;

@Mapper
public interface RegionMapper {
	long countByExample(RegionExample example);

	int deleteByExample(RegionExample example);

	int insert(Region record);

	int insertSelective(Region record);

	List<Region> selectByExample(RegionExample example);

	int updateByExampleSelective(@Param("record") Region record, @Param("example") RegionExample example);

	int updateByExample(@Param("record") Region record, @Param("example") RegionExample example);
}