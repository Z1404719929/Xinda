package com.datangedu.cn.dao.mapper;

import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.ProviderProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProviderProductMapper {
    long countByExample(ProviderProductExample example);

    int deleteByExample(ProviderProductExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProviderProduct record);

    int insertSelective(ProviderProduct record);

    List<ProviderProduct> selectByExample(ProviderProductExample example);

    ProviderProduct selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProviderProduct record, @Param("example") ProviderProductExample example);

    int updateByExample(@Param("record") ProviderProduct record, @Param("example") ProviderProductExample example);

    int updateByPrimaryKeySelective(ProviderProduct record);

    int updateByPrimaryKey(ProviderProduct record);
}