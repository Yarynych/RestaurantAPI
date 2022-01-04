package ua.yarynych.demoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yarynych.demoapi.model.Bar;
import ua.yarynych.demoapi.model.Menu;

import java.util.ArrayList;

@Service
public class OrderService {

    public int countCost(ArrayList<Bar> orderBar, ArrayList<Menu> orderMenu) {

        int sum = 0;

        for(Menu dish: orderMenu) {
            sum += dish.getPrice();
        }
        for(Bar drink: orderBar) {
            sum += drink.getPrice();
        }

        return sum;
    }
}
