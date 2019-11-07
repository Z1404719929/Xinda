package com.datangedu.cn.controller.provider;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.datangedu.cn.model.sysUser.Member;
import com.datangedu.cn.model.sysUser.Provider;
import com.datangedu.cn.model.sysUser.Region;

import org.springframework.web.multipart.MultipartFile;
import com.datang.hrb.util.MD5Util;
import com.datangedu.cn.servicegps.ProviderUserService;
import com.datangedu.cn.servicegps.RegionService;

@Controller
@RequestMapping("/provider2")
public class ControllerProvider2 {

		@Resource
		RegionService regionService;
		@Resource
		ProviderUserService providerUserService;
		
		@ResponseBody		//省
		@RequestMapping(value="/province",method = RequestMethod.POST)
		public Map<String,Object> getprovince(HttpServletRequest request) {
			Map<String,Object> map = new HashMap<String,Object>();
			List<Region> province = regionService.getProvince((short) 1);
			map.put("province", province);
			System.out.println("省id=="+province.get(0).getId());
			return map;
		}
		
		
		@ResponseBody		//市
		@RequestMapping(value="/county",method = RequestMethod.POST)
		public Map<String,Object> getcounty(HttpServletRequest request) {
			Map<String,Object> map = new HashMap<String,Object>();
			System.out.println("选择的省id=="+request.getParameter("id"));
			List<Region> county = regionService.getParent(request.getParameter("id"));
			map.put("county", county);
			System.out.println("市id=="+county.get(0).getId());
			return map;
		}
		
		
		@ResponseBody		//区
		@RequestMapping(value="/district",method = RequestMethod.POST)
		public Map<String,Object> getdistrict(HttpServletRequest request) {
			Map<String,Object> map = new HashMap<String,Object>();
			System.out.println("选择的市id=="+request.getParameter("id"));
			List<Region> district = regionService.getParent(request.getParameter("id"));
			map.put("district", district);
			System.out.println("区id=="+district.get(0).getId());
			return map;
		}
		
		@ResponseBody		//注册
		@RequestMapping(value="/register",method = RequestMethod.POST)
		public Map<String,Object> register(HttpServletRequest request) {
			Map<String,Object> map = new HashMap<String,Object>();
			if(request.getParameter("loginId").isEmpty()) {
				map.put("msg","输入手机号" );
				return map;
			}
			if(request.getParameter("password").isEmpty()) {
				map.put("msg","输入密码" );
				return map;
			}
			if(request.getParameter("code").isEmpty()) {
				map.put("msg","输入验证码" );
				return map;
			}
			
			List<Provider> provider=providerUserService.getLoginId(request.getParameter("loginId"));
			System.out.println(provider);
			if(!provider.isEmpty()) {
				map.put("msg","手机号已存在" );
				return map;
			}
			
			System.out.println("222");
			int a = providerUserService.register(request);		
			System.out.println("插入成功"+a);
			map.put("msg", "注册成功,点击确定自动返回登录页面");
			return map;
		}
		
		
		@ResponseBody
		@RequestMapping(value = "/findpassword",method = RequestMethod.POST)
		public Map <String,Object> findPassword(HttpServletRequest request) {
			Map<String,Object> map = new HashMap<String,Object>();
			Provider provider=new Provider();
			
			if(request.getParameter("loginId").isEmpty()) {
				map.put("msg","输入手机号" );
				return map;
			}
			if(request.getParameter("code").isEmpty()) {
				map.put("msg","输入验证码" );
				return map;
			}
			if(request.getParameter("password1").isEmpty()) {
				map.put("msg","输入密码" );
				return map;
			}
			if(request.getParameter("password2").isEmpty()) {
				map.put("msg","再输入密码" );
				return map;
			}
			if(!request.getParameter("password1").equals(request.getParameter("password2"))) {
				map.put("msg","两次密码不一致");
				return map;
			}
			provider.setLoginId(request.getParameter("loginId"));
			provider.setPassword(MD5Util.getMD5(request.getParameter("password1").getBytes()));
			int a=providerUserService.updatepassword(provider, request);
			System.out.println("修改密码5"+a);
			map.put("stu", a);
			if(a==1) {
			map.put("msg", "修改成功,点击确定自动返回登录页面");
			}else {
				map.put("msg", "账号不存在");
			}
			return map;
		}
		
		@ResponseBody
		@RequestMapping("/saveUserImg")
		public Map<String,Object> saveUserImg(MultipartFile file,String id) {
			Map<Object,Object> result = new HashMap<Object,Object>();
			try {
			// 获取客户端传图图片的输入流
			InputStream ins = file.getInputStream();
			byte[] buffer=new byte[1024];//bit---byte---1k---1m
			int len=0;
			 // 字节输出流
			 ByteArrayOutputStream bos=new ByteArrayOutputStream();
			while((len=ins.read(buffer))!=-1){
				bos.write(buffer,0,len);
			 }
			 bos.flush();
			byte data[] = bos.toByteArray();
			// 调用接口对图片进行存储
			Provider provider = new Provider();
			provider.setId(id);
			provider.setProviderImg(data);
			result.put("msg", "保存头像成功");
			System.out.println("保存头像saveUserImg"+data);
			providerUserService.saveUserImg(provider);
					            
			result.put("code",1);
			result.put("msg", "保存头像成功");
			} catch (Exception e) {
				result.put("code",0);
				result.put("msg", "保存头像失败");
				System.out.println("保存头像失败");
			}	
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("msg", "图片更新失败");
			return map;
		}

		
		@RequestMapping(value = "/provider_Img", produces = MediaType.IMAGE_PNG_VALUE)
		public ResponseEntity<byte[]> headImg( String id) throws Exception{

			byte[] imageContent ;
			// 根据id获取当前用户的信息
			Provider provider = providerUserService.getProviderUserInfo(id);
					        
			imageContent = provider.getProviderImg();
			System.out.println("图片==="+provider.getProviderImg());
					        
			// 设置http头部信息
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			// 返回响应实体
			return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
		}

}
