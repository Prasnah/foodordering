package com.prasanna.foodordering.statergy;

import com.prasanna.foodordering.entity.Order;
import com.prasanna.foodordering.entity.Restuarant;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LowestCost implements OrderingStratergy{
    double minimumCost = Double.MAX_VALUE;
    double totalCost = 0.0;
    boolean canFullFillTheOrder = true;
    private Restuarant selectedResturant = null;
    @Override
    public Restuarant findResturant(@NonNull final Order order, @NonNull final List<Restuarant> restuarants) {
        totalCost = 0.0;
        canFullFillTheOrder = true;
        for(Restuarant restuarant : restuarants){
            if(restuarant.getOrders().size() > restuarant.getMaximumOrders()){
                canFullFillTheOrder =  false;
                break;
            }
            for(Map.Entry<String, Integer> mp:order.getItems().entrySet()){

          if(!restuarant.getMenu().containsKey(mp.getKey())){
             canFullFillTheOrder =  false;
             break;
         }
          totalCost+=restuarant.getMenu().get(mp.getKey()) * mp.getValue();
           selectedResturant = restuarant;
         }
            if(canFullFillTheOrder && totalCost < minimumCost){
                minimumCost = totalCost;

            }
       }
        return selectedResturant;
    }
}
