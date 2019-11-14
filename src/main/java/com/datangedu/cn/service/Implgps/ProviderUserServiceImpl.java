package com.datangedu.cn.service.Implgps;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.dao.mapper.ProviderMapper;
import com.datangedu.cn.model.sysUser.Provider;
import com.datangedu.cn.model.sysUser.ProviderExample;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.ProviderProductExample;
import com.datangedu.cn.servicegps.ProviderUserService;
@Service
public class ProviderUserServiceImpl implements ProviderUserService{
	@Resource
	ProviderMapper providerMapper;
	
	//登录
		@Override
		public List<Provider> setProviderLogin(HttpServletRequest request) {
			System.out.println("登录账号==="+request.getParameter("loginId"));
			System.out.println("密码==="+request.getParameter("password"));
			ProviderExample providerExample = new ProviderExample();
			ProviderExample.Criteria criteria = providerExample.createCriteria();
			criteria.andLoginIdEqualTo(request.getParameter("loginId"));
			return providerMapper.selectByExample(providerExample);
		}
		
		//修改密码
		public int updatepassword(Provider provideruser,HttpServletRequest request) {
			ProviderExample providerExample = new ProviderExample();
			ProviderExample.Criteria criteria = providerExample.createCriteria();
			criteria.andLoginIdEqualTo(request.getParameter("loginId"));
			return providerMapper.updateByExampleSelective(provideruser, providerExample);
		}
		
		
			@Override
			public List<Provider> getList() {
				ProviderExample providerExample = new ProviderExample();
				ProviderExample.Criteria criteria = providerExample.createCriteria();
				return providerMapper.selectByExample(providerExample);
			}

			// 通过id查
			@Override
			public List<Provider> getProvider(String id) {
				ProviderExample providerExample = new ProviderExample();
				ProviderExample.Criteria criteria = providerExample.createCriteria();
				criteria.andIdEqualTo(id);
				return providerMapper.selectByExample(providerExample);
			}
			//注册插入用户
				public int register(HttpServletRequest request) {
					String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
					System.out.println("id=="+uuid);
					Provider p=new Provider();
					System.out.println("手机号"+request.getParameter("loginId"));
					System.out.println("密码"+request.getParameter("password"));

					java.sql.Date ctime = new java.sql.Date(new java.util.Date().getTime());
					p.setRegisterTime(ctime);
					p.setId(uuid);
					p.setName(request.getParameter("loginId"));
					p.setProviderInfo(request.getParameter("providerInfo"));
				    p.setCellphone(request.getParameter("cellphone"));
					p.setPassword(MD5Util.getMD5(request.getParameter("password").getBytes()));
					p.setStatus(1);
					p.setEmail(request.getParameter("email"));
					p.setLoginId(request.getParameter("loginId"));
					p.setQq(request.getParameter("qq"));
					p.setWorkTime("8:00-17:00");
					p.setRecommend(1);
					p.setProviderIntroduce(request.getParameter("providerIntroduce"));
					p.setWeixin(request.getParameter("loginId"));
					p.setRegionId(request.getParameter("district"));
					return providerMapper.insert(p);
				}
				
				
				   //通过手机号查
					@Override
					public List<Provider> getLoginId(String id) {
						ProviderExample providerExample = new ProviderExample();
						ProviderExample.Criteria criteria = providerExample.createCriteria();
						criteria.andCellphoneEqualTo(id);
						return providerMapper.selectByExample(providerExample);
					}
					
					
					//头像上传和展示
					@Override
					public void saveUserImg(Provider provider) throws Exception{
							int i = providerMapper.saveUserImg(provider);
							System.out.println("ppt==="+i);
							if(i!=1) {
								throw new Exception("更新用户头像失败");
							} 
						}
					
					//营业执照
					@Override
					public void saveFileImg(Provider provider) throws Exception{
							int i = providerMapper.saveFileImg(provider);
							System.out.println("iiiiiii==="+i);
							if(i!=1) {
								throw new Exception("更新用户营业执照失败");
							} 
						}
					
					@Override
					public Provider getProviderUserInfo(String id) {
						return providerMapper.selectByPrimaryKey(id);
					}
					
					// 通过id查
					@Override
					public List<Provider> getProviderId(String id) {
						ProviderExample providerExample = new ProviderExample();
						ProviderExample.Criteria criteria = providerExample.createCriteria();
						criteria.andIdEqualTo(id);
						return providerMapper.selectByExample(providerExample);
					}


}
