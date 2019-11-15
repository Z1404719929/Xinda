package com.datangedu.cn.servicegps;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datangedu.cn.model.sysUser.Provider;

public interface ProviderUserService {

	List<Provider> setProviderLogin(HttpServletRequest request);

	public int updatepassword(Provider provideruser, HttpServletRequest request);
	

	List<Provider> getList();

	List<Provider> getProvider(String id);

	int register(HttpServletRequest request);

	List<Provider> getLoginId(String id);

	void saveUserImg(Provider provider) throws Exception;

	List<Provider> getProviderId(String id);

	Provider getProviderUserInfo(String id);

	void saveFileImg(Provider provider) throws Exception;


}
