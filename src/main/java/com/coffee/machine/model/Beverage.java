package com.coffee.machine.model;

public class Beverage {

    final Recipe recipe;
    final String name;
    public Beverage(Recipe recipe, String name) {
        this.recipe = recipe;
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return name;
    }
}
