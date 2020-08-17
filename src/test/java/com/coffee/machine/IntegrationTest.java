package com.coffee.machine;

import com.coffee.machine.business.CoffeeMachine;
import com.coffee.machine.model.Beverage;
import com.coffee.machine.model.Ingredient;
import com.coffee.machine.model.Recipe;
import com.coffee.machine.model.Response;
import com.coffee.machine.service.BeveragePreparationService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Integration test class which tests the coffee maker
 * can be run via mvn test
 */
public class IntegrationTest {

    Ingredient hot_water;
    Ingredient hot_milk;
    Ingredient ginger_syrup;
    Ingredient sugar_syrup;
    Ingredient tea_leaves_syrup;
    Ingredient green_mixture;
    Beverage hot_tea;
    Beverage hot_coffee;
    Beverage black_tea;
    Beverage green_tea;


    @Before
    public void before() {
        hot_water = new Ingredient("hot_water");
        hot_milk = new Ingredient("hot_milk");
        ginger_syrup = new Ingredient("ginger_syrup");
        sugar_syrup = new Ingredient("sugar_syrup");
        tea_leaves_syrup = new Ingredient("tea_leaves_syrup");
        green_mixture = new Ingredient("green_mixture");
        Recipe hot_tea_recipe = new Recipe();
        hot_tea_recipe.addIngredient(hot_water, 200);
        hot_tea_recipe.addIngredient(hot_milk, 100);
        hot_tea_recipe.addIngredient(ginger_syrup, 10);
        hot_tea_recipe.addIngredient(sugar_syrup, 10);
        hot_tea_recipe.addIngredient(tea_leaves_syrup, 30);
        hot_tea = new Beverage(hot_tea_recipe, "hot_tea");

        Recipe hot_coffee_recipe = new Recipe();
        hot_coffee_recipe.addIngredient(hot_water, 100);
        hot_coffee_recipe.addIngredient(hot_milk, 400);
        hot_coffee_recipe.addIngredient(ginger_syrup, 30);
        hot_coffee_recipe.addIngredient(sugar_syrup, 50);
        hot_coffee_recipe.addIngredient(tea_leaves_syrup, 30);
        hot_coffee = new Beverage(hot_coffee_recipe, "hot_coffee");

        Recipe black_tea_recipe = new Recipe();
        black_tea_recipe.addIngredient(hot_water, 300);
        black_tea_recipe.addIngredient(ginger_syrup, 30);
        black_tea_recipe.addIngredient(sugar_syrup, 50);
        black_tea_recipe.addIngredient(tea_leaves_syrup, 30);
        black_tea = new Beverage(black_tea_recipe, "black_tea");


        Recipe green_tea_recipe = new Recipe();
        green_tea_recipe.addIngredient(hot_water, 100);
        green_tea_recipe.addIngredient(ginger_syrup, 30);
        green_tea_recipe.addIngredient(sugar_syrup, 50);
        green_tea_recipe.addIngredient(green_mixture, 30);
        green_tea = new Beverage(green_tea_recipe, "green_tea");

    }

    @Test
    public void testCoffeeMachine() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(3);
        coffeeMachine.addIngredient(hot_water, 500);
        coffeeMachine.addIngredient(hot_milk, 500);
        coffeeMachine.addIngredient(ginger_syrup, 100);
        coffeeMachine.addIngredient(sugar_syrup, 100);
        coffeeMachine.addIngredient(tea_leaves_syrup, 100);


        List<Beverage> beverageList = Arrays.asList(hot_tea, hot_coffee, black_tea, green_tea);

        BeveragePreparationService beveragePreparationService =
                new BeveragePreparationService(coffeeMachine);
        print(beveragePreparationService.prepareBeverages(beverageList));
    }

    @Test
    public void testCountOfBeverages() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(3);
        coffeeMachine.addIngredient(hot_water, 5000);
        coffeeMachine.addIngredient(hot_milk, 5000);
        coffeeMachine.addIngredient(ginger_syrup, 1000);
        coffeeMachine.addIngredient(sugar_syrup, 1000);
        coffeeMachine.addIngredient(tea_leaves_syrup, 1000);
        coffeeMachine.addIngredient(green_mixture, 1000);
        List<Beverage> beverageList = Arrays.asList(hot_tea, hot_coffee, black_tea, green_tea);

        BeveragePreparationService beveragePreparationService =
                new BeveragePreparationService(coffeeMachine);
        print(beveragePreparationService.prepareBeverages(beverageList));
    }

    @Test
    public void testThresholdLog() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(3);
        coffeeMachine.addIngredient(hot_water, 100);
        coffeeMachine.addIngredient(hot_milk, 50);
        coffeeMachine.addIngredient(ginger_syrup, 50);
        coffeeMachine.addIngredient(sugar_syrup, 50);
        coffeeMachine.addIngredient(green_mixture, 30);

        List<Beverage> beverageList = Arrays.asList(green_tea);
        BeveragePreparationService beveragePreparationService =
                new BeveragePreparationService(coffeeMachine);
        print(beveragePreparationService.prepareBeverages(beverageList));

    }

    private void print(Map<Beverage, Response> beverageResponseMap) {
        for (Beverage beverage : beverageResponseMap.keySet()) {
            if (beverageResponseMap.get(beverage).isSuccess()) {
                System.out.println(beverage + " is prepared");
            } else {
                System.out.println(beverageResponseMap.get(beverage).getReason());
            }
        }
    }
}