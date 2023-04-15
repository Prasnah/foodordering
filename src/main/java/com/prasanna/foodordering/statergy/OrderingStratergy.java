package com.prasanna.foodordering.statergy;

import com.prasanna.foodordering.entity.Order;
import com.prasanna.foodordering.entity.Restuarant;
import lombok.NonNull;

import java.util.List;

public interface OrderingStratergy {
    Restuarant findResturant(@NonNull Order order, @NonNull List<Restuarant> restuarants);
}

