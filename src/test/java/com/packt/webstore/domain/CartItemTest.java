package com.packt.webstore.domain;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartItemTest {
	private CartItem cartItem;
	
	@Before
	public void setup() {
		cartItem = new CartItem();
	}
	
	@Test
	public void cartItem_total_price_should_be_equal_to_product_unit_price_in_case_of_single_quantity() {
		// Ustaw
		Product iphone = new Product("P1234","iPhone 5s", new BigDecimal(500));
		cartItem.setProduct(iphone);
		// Wykonaj
		BigDecimal totalPrice = cartItem.getTotalPrice();
		// Porownaj
		Assert.assertEquals(iphone.getUnitPrice(), totalPrice);
	}

}
