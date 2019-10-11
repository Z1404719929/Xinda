package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.ServiceOrder;
import com.datangedu.cn.model.sysUser.ServiceOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceOrderMapper {
    long countByExample(ServiceOrderExample example);

    int deleteByExample(ServiceOrderExample example);

    int deleteByPrimaryKey(String serviceNo);

    int insert(ServiceOrder record);

    int insertSelective(ServiceOrder record);

    List<ServiceOrder> selectByExample(ServiceOrderExample example);

    ServiceOrder selectByPrimaryKey(String serviceNo);

    int updateByExampleSelective(@Param("record") ServiceOrder record, @Param("example") ServiceOrderExample example);

    int updateByExample(@Param("record") ServiceOrder record, @Param("example") ServiceOrderExample example);

    int updateByPrimaryKeySelective(ServiceOrder record);

    int updateByPrimaryKey(ServiceOrder record);
}