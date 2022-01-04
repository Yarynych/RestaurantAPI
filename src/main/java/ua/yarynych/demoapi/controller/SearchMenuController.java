package ua.yarynych.demoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yarynych.demoapi.model.Menu;
import ua.yarynych.demoapi.repository.MenuRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SearchMenuController {

    @Autowired
    MenuRepository menuRepository;


    ArrayList<Menu> resultingSearchMenu = new ArrayList<>();


    @GetMapping("/search")
    public String menuMain(Model model) {
        model.addAttribute("title", "Пошук");

        Iterable<Menu> menu = menuRepository.findAll();
        model.addAttribute("menu", menu);
        return "SEARCH";
    }

    @PostMapping("/search")
    public String findDish(@RequestParam String search, Model model) {

        model.addAttribute("title", "Результати пошуку");

        if(!resultingSearchMenu.isEmpty()) {
            resultingSearchMenu.clear();
        }

        Iterable<Menu> menu = menuRepository.findAll();
        Long id = null;

        for(Menu dish: menu) {
            if(dish.getName().equals(search)) {
                id = dish.getId();
            }
            else if (Integer.toString(dish.getPrice()).equals(search)) {
                id = dish.getId();
            }
        }

        assert id != null;
        try {
            Optional<Menu> menuSearch = menuRepository.findById(id);
            menuSearch.ifPresent(resultingSearchMenu::add);

            return "redirect:/search_detail";
        }
        catch (Exception e) {
            return "emptyList";
        }
    }

    @GetMapping("/search_detail")
    public String searchMainMenu(Model model) {
        model.addAttribute("title", "Пошук");

        model.addAttribute("menu", resultingSearchMenu);
        return "SEARCH";
    }


}
