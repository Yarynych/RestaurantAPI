package ua.yarynych.demoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yarynych.demoapi.model.Bar;
import ua.yarynych.demoapi.repository.BarRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SearchBarController {
    @Autowired
    BarRepository barRepository;

    ArrayList<Bar> resultingSearchBar = new ArrayList<>();

    @GetMapping("/search/bar")
    public String barMain(Model model) {
        model.addAttribute("title", "Пошук");

        Iterable<Bar> bar = barRepository.findAll();
        model.addAttribute("bar", bar);
        return "SEARCHBAR";
    }

    @PostMapping("/search/bar")
    public String findDrink(@RequestParam String search, Model model) {

        model.addAttribute("title", "Результати пошуку");

        if(!resultingSearchBar.isEmpty()) {
            resultingSearchBar.clear();
        }


        Iterable<Bar> bar = barRepository.findAll();

        Long id = null;

        for(Bar drink: bar) {
            if(drink.getName().equals(search)) {
                id = drink.getId();
            }
            else if (Integer.toString(drink.getPrice()).equals(search)) {
                id = drink.getId();
            }
        }

        try {
            Optional<Bar> barSearch = barRepository.findById(id);
            barSearch.ifPresent(resultingSearchBar::add);

            return "redirect:/search_detail/bar";
        }
        catch (Exception e) {
            return "emptyList";
        }
    }

    @GetMapping("/search_detail/bar")
    public String searchMainBar(Model model) {
        model.addAttribute("title", "Пошук");

        model.addAttribute("bar", resultingSearchBar);
        return "SEARCHBAR";
    }
}
