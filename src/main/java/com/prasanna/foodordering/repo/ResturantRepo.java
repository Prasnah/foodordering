package com.prasanna.foodordering.repo;

import com.prasanna.foodordering.entity.Restuarant;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResturantRepo {
    private final Map<String, Restuarant> restuarantRepo;

    public ResturantRepo() {
        this.restuarantRepo = new HashMap<>();
    }

    public void addRestuarant(@NonNull final Restuarant restuarant) {
        this.restuarantRepo.put(restuarant.getName(), restuarant);
    }

    public List<Restuarant> fetchAllRestuarant() {
        return new ArrayList<>(this.restuarantRepo.values());
    }
}
