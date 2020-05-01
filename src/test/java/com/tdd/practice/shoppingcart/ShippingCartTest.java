package com.tdd.practice.shoppingcart;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ShippingCartTest {

	@Test
	public void testAddproductToCart() {
		
		Product p = new Product(ProductName.APPLE, 0.60d);
		ShippingCart cart = new ShippingCart();
		cart.addToCart(p, 3);
		cart.addToCart(new Product(ProductName.ORANGE, 0.30d), 3);
		
		Assert.assertThat(2, Matchers.is(cart.size()));
	}
	
	@Test
	public void testModifyTheItemCount() {
		
		Product p = new Product(ProductName.APPLE, 0.60d);
		ShippingCart cart = new ShippingCart();
		cart.addToCart(p, 3);
		Product orange = new Product(ProductName.ORANGE, 0.30d);
		cart.addToCart(orange, 3);
		
		cart.reduceProductQuantity(orange, 1);
		Assert.assertThat(2, Matchers.is(cart.getProductQuantity(orange)));
	}
	
	@Test
	public void testCartCheckoutPrice() {
		
		Product p = new Product(ProductName.APPLE, 0.60d);
		ShippingCart cart = new ShippingCart();
		cart.addToCart(p, 3);
		Product orange = new Product(ProductName.ORANGE, 0.30d);
		cart.addToCart(orange, 3);
		
		cart.reduceProductQuantity(orange, 1);
		Assert.assertThat(2.40, Matchers.is(cart.checkout()));
	}
	
	@Test
	public void testBuyTwoAppleGetOneFree() {
		ThreeAppleOfPriceofTwo appleOffer = new ThreeAppleOfPriceofTwo();
		Product p = new Product(ProductName.APPLE, 0.60d);
		ShippingCart cart = new ShippingCart();
		cart.addOffer(appleOffer);
		
		cart.addToCart(p, 3);
		Product orange = new Product(ProductName.ORANGE, 0.30d);
		cart.addToCart(orange, 3);
		cart.reduceProductQuantity(orange, 1);
		DecimalFormat decimalFormat = new DecimalFormat("##.00");
		Assert.assertThat("1.80", Matchers.is(decimalFormat.format(cart.checkout())));
	}
	
	@Test
	public void testByOneGetOneOrangeFree() {
		BuyOneGetOneOrangeFree orangeOffer = new BuyOneGetOneOrangeFree();
		ThreeAppleOfPriceofTwo appleOffer = new ThreeAppleOfPriceofTwo();
		ShippingCart cart = new ShippingCart();
		cart.addOffer(orangeOffer);
		cart.addOffer(appleOffer);
		Product orange = new Product(ProductName.ORANGE, 0.30d);
		cart.addToCart(orange, 3);
		
		Product orangeMore = new Product(ProductName.ORANGE, 0.30d);
		cart.addToCart(orangeMore, 3);
		DecimalFormat decimalFormat = new DecimalFormat("##.00");
		Assert.assertThat("1.20", Matchers.is(decimalFormat.format(cart.checkout())));
		Assert.assertThat(4, Matchers.is(cart.getProductQuantity(orange)));
		Assert.assertThat(4, Matchers.is(cart.getProductQuantity(orangeMore)));
	}
}
