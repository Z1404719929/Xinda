package com.datangedu.cn.servicegps;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datangedu.cn.model.sysUser.ProviderProduct;
@Service
public interface ProviderProductService {
	public List<ProviderProduct> getpp();

	public List<ProviderProduct> getid(String id);

}
