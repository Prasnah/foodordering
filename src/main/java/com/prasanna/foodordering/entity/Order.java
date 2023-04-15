package com.prasanna.foodordering.entity;

import com.prasanna.foodordering.model.OrderStrategy;
import com.prasanna.foodordering.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
public class Order {
    private String user;
    private Map<String, Integer> items;
    private Restuarant restuarant;
    private Status orderStatus;
    private OrderStrategy orderStrategy;
    public Order(String _user,Map<String,Integer> _items,OrderStrategy _orderStrategy){
        this.user = _user;
        this.items = _items;
        this.orderStrategy = _orderStrategy;
    }
}
