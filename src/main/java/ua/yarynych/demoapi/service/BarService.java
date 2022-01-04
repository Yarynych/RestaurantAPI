package ua.yarynych.demoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yarynych.demoapi.model.Bar;
import ua.yarynych.demoapi.model.Menu;
import ua.yarynych.demoapi.repository.BarRepository;
import ua.yarynych.demoapi.repository.MenuRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class BarService {
    @Autowired
    BarRepository barRepository;

    public List<Bar> sortBarDecreasePrice(List<Bar> list) {
        Bar[] drinks = list.toArray(Bar[]::new);

        boolean isSorted = false;
        Bar buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < drinks.length-1; i++) {
                if(drinks[i].getPrice() < drinks[i+1].getPrice()){
                    isSorted = false;

                    buf = drinks[i];
                    drinks[i] = drinks[i+1];
                    drinks[i+1] = buf;
                }
            }
        }

        List<Bar> sortedBar = Arrays.asList(drinks);

        return sortedBar;
    }

    public List<Bar> sortBarIncreasePrice(List<Bar> list) {
        Bar[] drinks = list.toArray(Bar[]::new);

        boolean isSorted = false;
        Bar buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < drinks.length-1; i++) {
                if(drinks[i].getPrice() > drinks[i+1].getPrice()){
                    isSorted = false;

                    buf = drinks[i];
                    drinks[i] = drinks[i+1];
                    drinks[i+1] = buf;
                }
            }
        }

        List<Bar> sortedBar = Arrays.asList(drinks);

        return sortedBar;
    }

    public List<Bar> sortBarDecreasePopularity(List<Bar> list) {
        Bar[] drinks = list.toArray(Bar[]::new);

        boolean isSorted = false;
        Bar buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < drinks.length-1; i++) {
                if(drinks[i].getPopularity() < drinks[i+1].getPopularity()){
                    isSorted = false;

                    buf = drinks[i];
                    drinks[i] = drinks[i+1];
                    drinks[i+1] = buf;
                }
            }
        }

        List<Bar> sortedBar = Arrays.asList(drinks);

        return sortedBar;
    }

    public List<Bar> sortBarIncreasePopularity(List<Bar> list) {
        Bar[] drinks = list.toArray(Bar[]::new);

        boolean isSorted = false;
        Bar buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < drinks.length-1; i++) {
                if(drinks[i].getPopularity() > drinks[i+1].getPopularity()){
                    isSorted = false;

                    buf = drinks[i];
                    drinks[i] = drinks[i+1];
                    drinks[i+1] = buf;
                }
            }
        }

        List<Bar> sortedBar = Arrays.asList(drinks);

        return sortedBar;
    }
}
