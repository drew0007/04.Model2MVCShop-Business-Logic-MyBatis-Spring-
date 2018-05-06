package com.model2.mvc.service.user.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class CartServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	
	//@Test
	public void testAddCart() throws Exception {
		User user = new User();
		user.setUserId("user01");
		
		Product product = new Product();
		product.setProdNo(10037);
		
		Cart cart = new Cart();
		cart.setCartCnt(5);
		cart.setCartProd(product);
		cart.setCartUser(user);

		cartService.addCart(cart);
	}
	
	//@Test
	public void testGetCart() throws Exception {
		Cart cart = new Cart();

		cart = cartService.getCart("user01",10036);

		Assert.assertEquals(10028, cart.getCartNo());
		Assert.assertEquals("user01", cart.getCartUser().getUserId());
		Assert.assertEquals(10036, cart.getCartProd().getProdNo());
		Assert.assertEquals(11, cart.getCartCnt());

		//Assert.assertNotNull(userService.getUser("user02"));
		//Assert.assertNotNull(userService.getUser("user05"));
	}
		
	//@Test
	public void testUpdateCart() throws Exception{
		Cart cart = new Cart();
		cart = cartService.getCart("user01", 10036);

		cart.setCartCnt(5);
		
		cartService.updateCart(cart);
		
		cart = cartService.getCart("user01", 10036);
		Assert.assertEquals(5, cart.getCartCnt());
	}
	
	//@Test
	public void testDeleteCart() throws Exception{	
		Cart cart = new Cart();
		
		cartService.deleteCart(10028);
	}
	
	//@Test
	public void testGetCartList() throws Exception{
	 
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(5);
		Map<String,Object> map = cartService.getCartList(search, "user01");
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("=======================================");
		
		search.setCurrentPage(1);
		search.setPageSize(5);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = cartService.getCartList(search, "user01");
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		//==> console 확인
		//System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}

	
	//@Test
	public void testGetCartList2() throws Exception{
	
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(5);
		Map<String,Object> map = cartService.getCartList2("10029,10030,");
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(2, list.size());
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("=======================================");
		
		search.setCurrentPage(1);
		search.setPageSize(5);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = cartService.getCartList2("10029,10030,");
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(2, list.size());
		
		//==> console 확인
		//System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}	
}