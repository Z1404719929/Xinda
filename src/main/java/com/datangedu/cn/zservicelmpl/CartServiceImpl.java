package com.datangedu.cn.zservicelmpl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.datangedu.cn.dao.mapper.CartMapper;
import com.datangedu.cn.model.sysUser.Cart;
import com.datangedu.cn.model.sysUser.CartExample;
import com.datangedu.cn.model.sysUser.Member;
import com.datangedu.cn.model.sysUser.MemberExample;
import com.datangedu.cn.model.sysUser.ProviderProduct;
import com.datangedu.cn.model.sysUser.ProviderProductExample;
import com.datangedu.cn.zservice.CartService;
import com.datangedu.cn.zservice.MemberService;
@Service
public class CartServiceImpl  implements  CartService{
	
	
	@Resource
	CartMapper cartMapper;
	
	//从点击所有的产品中得到id并存到购物车中
	@Override
	public int insertCart(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
	   	System.out.println(id);
	       Cart cart=new Cart();
	   	System.out.println("插入成功");
	cart.setServiceId(id);
	cart.setBuyNum(1);
		return cartMapper.insert(cart);
	}
	
	//查询所有
	@Override
	public List<Cart> getcartid(HttpServletRequest request) {	
		CartExample cartExample = new CartExample();
		CartExample.Criteria criteria = cartExample.createCriteria();
		System.out.println("156655");
		
		criteria.andUserIdEqualTo(request.getParameter("userid"));
		return cartMapper.selectByExample(cartExample);
	}

	//通过id查
	@Override
	public List<Cart> getcid(String id) {	
		CartExample cartExample = new CartExample();
		CartExample.Criteria criteria = cartExample.createCriteria();
		criteria.andServiceIdEqualTo(id);
		return cartMapper.selectByExample(cartExample);
	}


	//通过id修改数量
	public int updateBuynum(Cart cart,String id) {
		CartExample cartExample = new CartExample();
		CartExample.Criteria criteria = cartExample.createCriteria();
		criteria.andServiceIdEqualTo(id);
		return cartMapper.updateByExampleSelective(cart, cartExample);
	}
	

}