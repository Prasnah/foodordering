package com.prasanna.foodordering;

import com.prasanna.foodordering.entity.Order;
import com.prasanna.foodordering.entity.Restuarant;
import com.prasanna.foodordering.exception.InvalidOrderCompleteRequest;
import com.prasanna.foodordering.model.OrderStrategy;
import com.prasanna.foodordering.model.Status;
import com.prasanna.foodordering.service.ResturantService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.internal.matchers.Or;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Map;

import static java.util.Map.entry;

@SpringBootTest
class FoodorderingApplicationTests {
	ResturantService resturantService;
	Restuarant restuarant_1;
	Restuarant restuarant_2;
	Restuarant restuarant_3;
	Restuarant restuarant_4;
	@BeforeEach
	void contextLoads() {
		 restuarant_1 = new Restuarant("R1",5, Map.ofEntries(entry("idli", 10.0),entry("pongal", 18.0)), 3.0, new ArrayList<>());
	     restuarant_2 = new Restuarant("R2",20, Map.ofEntries(entry("briyani", 100.0),entry("leg piece", 90.0)), 3.7, new ArrayList<>());
		 restuarant_3 = new Restuarant("R3",2, Map.ofEntries(entry("pizza", 40.0),entry("briyani", 78.0)), 4.5, new ArrayList<>());
		 restuarant_4 = new Restuarant("R4",2, Map.ofEntries(entry("pizza", 39.0),entry("briyani", 78.0)), 4.5, new ArrayList<>());
		 resturantService = new ResturantService();
	}

	@Test
	@SneakyThrows
	void onBoardRestuarnt(){
		resturantService.onBoardResturant(restuarant_1);
		resturantService.onBoardResturant(restuarant_2);
		resturantService.onBoardResturant(restuarant_3);
		resturantService.onBoardResturant(restuarant_4);
		Assertions.assertEquals(resturantService.fetchAllResturants().size(),4);
		// Assertions.assertEquals(resturantService.fetchAllResturants().get(0).getName(),"R1");

		Order order_1 = new Order("Prasanna", Map.ofEntries(entry("pizza",2),entry("briyani",1)), OrderStrategy.LOW_COST);
	    Assertions.assertEquals(resturantService.placeOrder(order_1).getName(), "R4");
		try {
			Thread.sleep(5000);
		} catch(Exception e){

		}
		Assertions.assertEquals("R4",resturantService.completeOrder(order_1).getRestuarant().getName());
		Assertions.assertThrows(InvalidOrderCompleteRequest.class ,() -> resturantService.completeOrder(order_1).getOrder().getOrderStatus());
	}

}
