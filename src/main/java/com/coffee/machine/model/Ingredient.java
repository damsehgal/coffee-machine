package com.coffee.machine.model;

public class Ingredient {
    final String name;
    final int threshold;

    public Ingredient(String name) {
        this(name, 0);
    }

    public Ingredient(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Ingredient) obj).getName());
    }
}
