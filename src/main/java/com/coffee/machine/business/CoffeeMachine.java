package com.coffee.machine.business;

import com.coffee.machine.model.Beverage;
import com.coffee.machine.model.Ingredient;
import com.coffee.machine.model.Response;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine {
    int outlets;
    Map<Ingredient, Integer> ingredientQuantity;

    public CoffeeMachine(int outlets) {
        this.outlets = outlets;
        ingredientQuantity = new HashMap<>();
    }

    /**
     * adds specified quantity of  ingredient to the coffee maker
     * @param ingredient
     * @param quantity
     */
    public void addIngredient(Ingredient ingredient, Integer quantity) {
        ingredientQuantity.put(ingredient,
                quantity + ingredientQuantity.getOrDefault(ingredient, 0));
    }

    public int getOutlets() {
        return outlets;
    }

    /**
     * Checks weather a given beverage can be prepared using ingredients present in coffee maker
     * @param beverage
     * @return Response true if beverage can be prepared using the ingredients
     */
    public Response canPrepareBeverage(Beverage beverage) {
        for (Ingredient ingredient :  beverage.getRecipe().getIngredients()) {
            if (beverage.getRecipe().getIngredientQuantity(ingredient) >
                    ingredientQuantity.getOrDefault(ingredient, 0)) {
                return new Response(false, beverage.toString() + " cannot be prepared because "
                        + ingredient.getName() + " is not available");
            }
        }
        return new Response(true);
    }

    /**
     * prepares a beverage and consumes the ingredients
     * also logs weather an ingredient has reached threshold after making this beverage
     * @param beverage
     */
    public void prepareBeverage(Beverage beverage)  {
        if(canPrepareBeverage(beverage).isSuccess()) {
           for (Ingredient ingredient : beverage.getRecipe().getIngredients()) {
               ingredientQuantity.put(ingredient, 
                       ingredientQuantity.get(ingredient) - beverage.getRecipe().getIngredientQuantity(ingredient));
               if (ingredientQuantity.get(ingredient) <= ingredient.getThreshold()) {
                   System.err.println("Ingredient " + ingredient.getName() +
                           " reached to lower quantity then threshold: "  + ingredient.getThreshold() + " please refill.");
               }
           }
        } else {
            throw new RuntimeException("Can't prepare beverage: " + beverage);
        }
    }
}
