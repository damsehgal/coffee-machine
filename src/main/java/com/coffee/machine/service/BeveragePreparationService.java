package com.coffee.machine.service;

import com.coffee.machine.model.Beverage;
import com.coffee.machine.business.CoffeeMachine;
import com.coffee.machine.model.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * service class used for preparing a list of beverages by a particular coffee machine
 */
public class BeveragePreparationService {

    CoffeeMachine coffeeMachine;

    public BeveragePreparationService(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    /**
     * @param beverageList list of beverages that are ordered
     * @return returns the map o beverage with response code
     */
    public Map<Beverage, Response> prepareBeverages(List<Beverage> beverageList) {
        Map<Beverage, Response> beverageResponseMap = new HashMap<>();
        int count =  0;
        for (Beverage beverage : beverageList) {
            Response response = coffeeMachine.canPrepareBeverage(beverage);
            if (response.isSuccess()) {
                if (count < coffeeMachine.getOutlets()) {
                    coffeeMachine.prepareBeverage(beverage);
                    count++;
                } else {
                    response.setSuccess(false);
                    response.setReason("Cannot prepare " + beverage.toString() + " because all outlets are occupied");
                }
            }
            beverageResponseMap.put(beverage, response);
        }
        return beverageResponseMap;
    }
}
