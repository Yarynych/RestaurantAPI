package ua.yarynych.demoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yarynych.demoapi.model.Bar;
import ua.yarynych.demoapi.model.Menu;
import ua.yarynych.demoapi.repository.BarRepository;
import ua.yarynych.demoapi.service.BarService;
import ua.yarynych.demoapi.service.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BarController {

    @Autowired
    private BarRepository barRepository;

    @Autowired
    private BarService barService;

    @GetMapping("/bar")
    public String barMain(Model model) {
        model.addAttribute("title", "Бар");

        Iterable<Bar> bar = barRepository.findAll();
        model.addAttribute("bar", bar);
        return "bar_main";
    }

    @GetMapping("/bar/add")
    public String barAdd(Model model) {
        model.addAttribute("title", "Додати напій");
        return "bar_add";
    }

    @PostMapping("/bar/add")
    public String addBar(@RequestParam String name, @RequestParam String preview,
                          @RequestParam String description, @RequestParam int price, Model model) {
        int popularity = 0;
        Bar bar = new Bar(name, preview, description, price, popularity);
        barRepository.save(bar);
        return "redirect:/bar";
    }

    @GetMapping("/bar/{id}")
    public String barDetail(@PathVariable(value = "id") long id, Model model) {

        if(!barRepository.existsById(id)) {
            return "redirect:/bar";
        }

        model.addAttribute("nameOfPage", "Напій " + id);
        Optional<Bar> bar = barRepository.findById(id);
        ArrayList<Bar> resultingBar = new ArrayList<>();
        bar.ifPresent(resultingBar::add);
        model.addAttribute("bar", resultingBar);
        return "bar_details";
    }

    @GetMapping("/bar/{id}/edit")
    public String barEdit(@PathVariable(value = "id") long id, Model model) {

        if(!barRepository.existsById(id)) {
            return "redirect:/bar";
        }

        model.addAttribute("nameOfPage", "Редагування напоїв " + id);
        Optional<Bar> bar = barRepository.findById(id);
        ArrayList<Bar> resultingBar = new ArrayList<>();
        bar.ifPresent(resultingBar::add);
        model.addAttribute("bar", resultingBar);
        return "bar_edit";
    }

    @PostMapping("/bar/{id}/edit")
    public String barUpdate(@PathVariable(value = "id") long id,
                             @RequestParam String name, @RequestParam String preview,
                             @RequestParam String description, @RequestParam int price, Model model) {
        Bar bar = barRepository.findById(id).orElseThrow();
        bar.setName(name);
        bar.setPreview(preview);
        bar.setDescription(description);
        bar.setPrice(price);

        barRepository.save(bar);
        return "redirect:/bar";
    }

    @PostMapping("/bar/{id}/remove")
    public String barDelete(@PathVariable(value = "id") long id, Model model) {
        Bar bar = barRepository.findById(id).orElseThrow();
        barRepository.delete(bar);
        return "redirect:/bar";
    }

    @GetMapping("/bar/filter")
    public String filterMain(Model model) {
        model.addAttribute("title", "Бар");

        Iterable<Bar> bar = barRepository.findAll();
        model.addAttribute("bar", bar);
        return "bar_main";
    }

    @PostMapping("/bar/filter")
    public String menuFilter(@RequestParam(value = "price_filter", required = false) String checkboxValue,
                             Model model) {
        if(checkboxValue.equals("price_decrease"))
        {
            model.addAttribute("title", "Бар");
            List<Bar> bar = (List<Bar>) barRepository.findAll();

            model.addAttribute("bar", barService.sortBarDecreasePrice(bar));
            return "bar_main";
        }
        else if(checkboxValue.equals("price_increase"))
        {
            model.addAttribute("title", "Бар");
            List<Bar> bar = (List<Bar>) barRepository.findAll();

            model.addAttribute("bar", barService.sortBarIncreasePrice(bar));
            return "bar_main";
        }
        else {
            return "redirect:/bar";
        }
    }

    @GetMapping("/filter/popularity/bar")
    public String filterPopularityMain(Model model) {
        model.addAttribute("title", "Бар");

        Iterable<Bar> bar = barRepository.findAll();
        model.addAttribute("bar", bar);
        return "bar_main";
    }

    @PostMapping("/filter/popularity/bar")
    public String barPopularityFilter(@RequestParam(value = "popularity_filter", required = false) String checkboxValue,
                                       Model model) {
        if(checkboxValue.equals("popularity_decrease"))
        {
            model.addAttribute("title", "Бар");
            List<Bar> bar = (List<Bar>) barRepository.findAll();

            model.addAttribute("bar", barService.sortBarDecreasePopularity(bar));
            return "bar_main";
        }
        else if(checkboxValue.equals("popularity_increase"))
        {
            model.addAttribute("title", "Бар");
            List<Bar> bar = (List<Bar>) barRepository.findAll();

            model.addAttribute("bar", barService.sortBarIncreasePopularity(bar));
            return "bar_main";
        }
        else {
            return "redirect:/bar";
        }
    }
}
