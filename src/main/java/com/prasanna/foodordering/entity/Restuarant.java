package com.prasanna.foodordering.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Restuarant {
    private String name;
    private int maximumOrders;
    private Map<String, Double> menu;
    private double ratings;
    private List<Order> orders;

    public Restuarant(String _name, int _maximumOrders, Map<String, Double> _menu, double _ratings, List<Order> _orders) {
        this.name = _name;
        this.menu = _menu;
        this.maximumOrders = _maximumOrders;
        this.ratings = _ratings;
        this.orders = _orders;
    }
}
