package com.prasanna.foodordering.service;

import com.prasanna.foodordering.entity.Order;
import com.prasanna.foodordering.entity.Restuarant;
import com.prasanna.foodordering.exception.InvalidOrderCompleteRequest;
import com.prasanna.foodordering.exception.NoMatchingResturantFound;
import com.prasanna.foodordering.exception.ResturantAlreadyExitsException;
import com.prasanna.foodordering.model.OrderStrategy;
import com.prasanna.foodordering.model.OrderSummary;
import com.prasanna.foodordering.model.Status;
import com.prasanna.foodordering.repo.ResturantRepo;
import com.prasanna.foodordering.statergy.LowestCost;
import com.prasanna.foodordering.statergy.OrderingStratergy;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public class ResturantService {
    ResturantRepo resturantRepo;
    OrderingStratergy orderingStratergy;
    public ResturantService(){
        resturantRepo = new ResturantRepo();
    }
    public void onBoardResturant(@NonNull final Restuarant restuarant) throws ResturantAlreadyExitsException {
       List<Restuarant> allResturants  = fetchAllResturants();
       Optional<Restuarant> restuarantExists = allResturants.stream().filter(_restuarant -> restuarant.getName().equals(_restuarant.getName())).findAny();
       if(restuarantExists.isPresent()){
           throw new ResturantAlreadyExitsException();
       }
       resturantRepo.addRestuarant(restuarant);
    }
    public List<Restuarant> fetchAllResturants(){
        return resturantRepo.fetchAllRestuarant();
    }

    public Restuarant placeOrder(@NonNull final Order order) throws NoMatchingResturantFound {
        List<Restuarant> allResturants  = fetchAllResturants();
        Restuarant selectedRestuarant = findResturant(order,allResturants);
        if(selectedRestuarant == null){
            throw new NoMatchingResturantFound("No Matching Resturant Found");
        }
        selectedRestuarant.getOrders().add(order);
        order.setOrderStatus(Status.ACCEPTED);
        order.setRestuarant(selectedRestuarant);
        return selectedRestuarant;
    }

    public OrderSummary completeOrder(@NonNull final Order order) throws InvalidOrderCompleteRequest {
        if(order.getOrderStatus().equals(Status.COMPLETED)){
            throw new InvalidOrderCompleteRequest();
        }
        order.setOrderStatus(Status.COMPLETED);
        OrderSummary orderSummary = new OrderSummary(order.getRestuarant(), order);
        return orderSummary;
    }
    private Restuarant findResturant(@NonNull final Order order,@NonNull final List<Restuarant> allResturants){
        if(order.getOrderStrategy().equals(OrderStrategy.LOW_COST)){
            orderingStratergy = new LowestCost();
        }
        return orderingStratergy.findResturant(order, allResturants);
    }
}
