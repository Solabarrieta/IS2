package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import domain.Article;
import domain.Purchase;
import exceptions.PurchaseException;


	public class PurchaseAddBasketTest {
		// Variables comunes para los casos de prueba.
		private Purchase basket= new Purchase();
		private double price;
		private int quantity=1;
		private Article article;
		// Inicialización a realizar antes de nada cuando se van a ejecutar los casos
		// de prueba de la clase
		
		@BeforeEach
		public void initialize() {
			System.out.println("Inicializo y compruebo ...");
			assertNull(basket.getDate());
			assertEquals(basket.getCost(), 0);
			price= 234.99; // 234.99 real value
			quantity = 1;
			article = new Article("404", "MASK PINK", price, false, quantity);
		}

		@Test
		@DisplayName("Test 1: ...")
		public void testAddBasket1() {
			quantity = 1;
			price = 234.99;
			double expected = quantity*price;
			double obtained = basket.addBasket(article, quantity);
			assertEquals(expected, obtained); // expected siempre 1er parámetro
			try {
				basket.removeBasket(article, quantity);
				assertTrue(true);
			} catch (PurchaseException e) {
				fail("Impossible!!");
			}
		}
		@Test
		@DisplayName("Test 2: ...")
		public void testAddBasket2() {
			quantity = 3;
			price = 234.99;
			assertThrows(RuntimeException.class,()-> basket.addBasket(article, quantity));
		}
		
		@Test
		@DisplayName("Test 3: ...")
		public void testAddBasket3() {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				java.util.Date date = format.parse("23/12/2020");
				basket.buy(date);
				assertNotNull(basket.getDate());
				quantity = 3;
				assertThrows(RuntimeException.class,()-> basket.addBasket(article, quantity));
			} catch (ParseException e) {
				fail("The date 23/12/2020 is correct");
			}
		}

}
