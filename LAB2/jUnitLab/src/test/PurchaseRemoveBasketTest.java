package test;

import exceptions.PurchaseException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import domain.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class PurchaseRemoveBasketTest {
	
	private Purchase basket= new Purchase();
	private double price;
	private int q;
	private Article article;
	int stock;

	@BeforeEach
	public void initialize() {
		System.out.println("Inicializo y compruebo ...");
		price = 234.99;
		q=3;
		article = new Article("404", "MASK PINK", price, false, q);
		stock = article.getStock();
		basket.addBasket(article, q);
		
	}
	
	@Test
	@DisplayName("Test 1: date != null")
	public void testRemoveBasket1() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date date = format.parse("23/12/2020");
			basket.buy(date);
		}catch(ParseException e) {
			fail("The date 23/12/2020 is correct");
			
		}
		assertThrows(RuntimeException.class, ()-> basket.removeBasket(article, q));
	}
	@Test 
	@DisplayName("Test 2: article = null")
	
	public void testRemoveBasket2() {
		assertThrows(exceptions.PurchaseException.class, ()-> basket.removeBasket(null, q));
	}
	
	@Before
	public void initialize2() {
		System.out.println("Inicializo y compruebo ...");
		price = 234.99;
		q=3;
		stock = article.getStock();
		basket.addBasket(article, q);
		article = new Article("404", "MASK PINK", price, false, q);
		
	}
	
	@Test 
	@DisplayName("Test 3: quantity < q")
	
	public void testRemoveBasket3() {
		int quantity=4;
		assertThrows(exceptions.PurchaseException.class, ()-> basket.removeBasket(article, quantity));
	}
	
	@Test
	@DisplayName("Test 4: quantity > q")
	
	public void testRemoveBasket4() {
		int quantity = 2;
		int expected = q-quantity;
		try {
			basket.removeBasket(article, quantity);
		}catch(exceptions.PurchaseException p) {
			p.printStackTrace();
		}
		int stockActual = article.getStock();
		int obtained = stock-stockActual;
		assertEquals(expected,obtained);		
	}
	
	@Test
	@DisplayName("Test 4: quantity = q")
	
	public void testRemoveBasket5() {
		int quantity = 3;
		int expected = 0;
		try {
			basket.removeBasket(article, quantity);
		}catch(exceptions.PurchaseException p) {
			p.printStackTrace();
		}
		int stockActual = article.getStock();
		int obtained = stock-stockActual;
		assertEquals(expected,obtained);		
	}
	
	
	

}
