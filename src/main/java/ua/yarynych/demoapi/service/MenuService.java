package ua.yarynych.demoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yarynych.demoapi.model.Menu;
import ua.yarynych.demoapi.repository.MenuRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public List<Menu> sortMenuDecreasePrice(List<Menu> list) {
        Menu[] dishes = list.toArray(Menu[]::new);

        boolean isSorted = false;
        Menu buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < dishes.length-1; i++) {
                if(dishes[i].getPrice() < dishes[i+1].getPrice()){
                    isSorted = false;

                    buf = dishes[i];
                    dishes[i] = dishes[i+1];
                    dishes[i+1] = buf;
                }
            }
        }

        List<Menu> sortedMenu = Arrays.asList(dishes);

        return sortedMenu;
    }

    public List<Menu> sortMenuIncreasePrice(List<Menu> list) {
        Menu[] dishes = list.toArray(Menu[]::new);

        boolean isSorted = false;
        Menu buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < dishes.length-1; i++) {
                if(dishes[i].getPrice() > dishes[i+1].getPrice()){
                    isSorted = false;

                    buf = dishes[i];
                    dishes[i] = dishes[i+1];
                    dishes[i+1] = buf;
                }
            }
        }

        List<Menu> sortedMenu = Arrays.asList(dishes);

        return sortedMenu;
    }

    public List<Menu> sortMenuDecreasePopularity(List<Menu> list) {
        Menu[] dishes = list.toArray(Menu[]::new);

        boolean isSorted = false;
        Menu buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < dishes.length-1; i++) {
                if(dishes[i].getPopularity() < dishes[i+1].getPopularity()){
                    isSorted = false;

                    buf = dishes[i];
                    dishes[i] = dishes[i+1];
                    dishes[i+1] = buf;
                }
            }
        }

        List<Menu> sortedMenu = Arrays.asList(dishes);

        return sortedMenu;
    }

    public List<Menu> sortMenuIncreasePopularity(List<Menu> list) {
        Menu[] dishes = list.toArray(Menu[]::new);

        boolean isSorted = false;
        Menu buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < dishes.length-1; i++) {
                if(dishes[i].getPopularity() > dishes[i+1].getPopularity()){
                    isSorted = false;

                    buf = dishes[i];
                    dishes[i] = dishes[i+1];
                    dishes[i+1] = buf;
                }
            }
        }

        List<Menu> sortedMenu = Arrays.asList(dishes);

        return sortedMenu;
    }
}
