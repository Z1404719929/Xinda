
package com.datangedu.cn.controller.providerproduct;

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
import org.springframework.web.multipart.MultipartFile;

import com.datangedu.cn.model.sysUser.Provider;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.servicegps.ProviderProductService;
import com.datangedu.cn.servicegps.ProviderProductService2;

@Controller
@RequestMapping("/provider")
public class ControllerProviderProduct {
	@Resource
	ProviderProductService2 providerProductService;

	@ResponseBody
	@RequestMapping(value = "/getproviderinfo", method = RequestMethod.GET)
	public Map<String, Object> ProviderInfo() {
		System.out.println("1111");
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProviderProduct> providerInfo = providerProductService.getProviderInfoByld();
		map.put("providerInfo", providerInfo);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/apa", method = RequestMethod.GET)
	public Map<String, Object> Apa(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String providerInfo = providerProductService.setProviderApa(request);
		map.put("providerInfo", providerInfo);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/providerregister", method = RequestMethod.POST)
	public Map<String, Object> providerRegister(HttpServletRequest request,MultipartFile file) {

		Map<String, Object> map = new HashMap<String, Object>();
		
			int providerInfo = providerProductService.setProviderRegister(request);
				
		if (providerInfo == 2) {
			map.put("msg", "输入有误");
		} else {
			map.put("msg", "添加成功");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/providerdelete", method = RequestMethod.POST)
	public Map<String,Object> providerDelete(HttpServletRequest request) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		int providerInfo=providerProductService.setProviderDelete(request);
		map.put("msg","恭喜删除成功");
		map.put("code",1);
		return map;
	}
	
	//上线
	@ResponseBody
	@RequestMapping(value = "/provideroline", method = RequestMethod.POST)
	public Map<String,Object> providerOline(HttpServletRequest request) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		ProviderProduct pp=new ProviderProduct();
		pp.setId(request.getParameter("id"));
		int a=providerProductService.providerOline(pp, request.getParameter("id"));
		return map;
	}
	//下线
		@ResponseBody
		@RequestMapping(value = "/providerdownline", method = RequestMethod.POST)
		public Map<String,Object> providerDown(HttpServletRequest request) {
			
			Map<String,Object> map=new HashMap<String,Object>();
			ProviderProduct pp=new ProviderProduct();
			pp.setId(request.getParameter("id"));
			int a=providerProductService.providerDown(pp, request.getParameter("id"));
			return map;
		}
		
		//产品图片
				@ResponseBody
				@RequestMapping("/savePpImg")
				public Map<String,Object> saveServiceImg(MultipartFile file,String id) {
					Map<Object,Object> result = new HashMap<Object,Object>();
					System.out.println("yhid"+id);
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
					ProviderProduct providerProduct = new ProviderProduct();
					providerProduct.setId(id);
					providerProduct.setServiceImg(data);
					result.put("msg", "保存图片成功");
					System.out.println("保存图片saveServiceImg"+data);
					providerProductService.saveServiceImg(providerProduct);
							            
					result.put("code",1);
					result.put("msg", "保存图片成功");
					} catch (Exception e) {
						result.put("code",0);
						result.put("msg", "保存图片失败");
						System.out.println("保存图片失败");
					}	
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("msg", "图片更新失败");
					return map;
				}

}

