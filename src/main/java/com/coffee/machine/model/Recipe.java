package com.coffee.machine.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Recipe {
    public final Map<Ingredient, Integer> ingredientMap;

    public Recipe() {
        this.ingredientMap = new HashMap<>();
    }
    public void addIngredient(Ingredient ingredient, Integer quantity) {
        this.ingredientMap.put(ingredient, quantity);
    }

    public Set<Ingredient> getIngredients() {
        return ingredientMap.keySet();
    }

    public Integer getIngredientQuantity(Ingredient ingredient) {
        return ingredientMap.get(ingredient);
    }
}
