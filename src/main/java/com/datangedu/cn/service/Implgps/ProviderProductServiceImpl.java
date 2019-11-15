package com.datangedu.cn.service.Implgps;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.ProviderProductMapper;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.ProviderProductExample;
import com.datangedu.cn.servicegps.ProviderProductService;

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 *            佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
*/
@Service
public class ProviderProductServiceImpl implements ProviderProductService {

	@Resource
	ProviderProductMapper providerProductMapper;

	// 查询所有产品
	@Override
	public List<ProviderProduct> getpp() {
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		System.out.println("查询结果几条===" + providerProductMapper.selectByExample(providerProductExample).size());
		return providerProductMapper.selectByExample(providerProductExample);
	}

	// 通过产品id查
	@Override
	public List<ProviderProduct> getid(String id) {
		ProviderProductExample providerProductExample = new ProviderProductExample();
		ProviderProductExample.Criteria criteria = providerProductExample.createCriteria();
		criteria.andIdEqualTo(id);
		return providerProductMapper.selectByExample(providerProductExample);
	}
	
	
	

}
