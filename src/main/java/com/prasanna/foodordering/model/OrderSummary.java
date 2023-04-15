package com.prasanna.foodordering.model;

import com.prasanna.foodordering.entity.Order;
import com.prasanna.foodordering.entity.Restuarant;
import com.prasanna.foodordering.statergy.OrderingStratergy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class OrderSummary {
    Restuarant restuarant;
    Order order;
}
