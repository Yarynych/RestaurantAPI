package ua.yarynych.demoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yarynych.demoapi.model.Menu;
import ua.yarynych.demoapi.repository.MenuRepository;
import ua.yarynych.demoapi.service.MenuService;

import java.util.*;

@Controller
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public String menuMain(Model model) {
        model.addAttribute("title", "Меню");

        Iterable<Menu> menu = menuRepository.findAll();
        model.addAttribute("menu", menu);
        return "menu_main";
    }

    @GetMapping("/menu/add")
    public String menuAdd(Model model) {
        model.addAttribute("title", "Додати страву");
        return "menu_add";
    }

    @PostMapping("/menu/add")
    public String addMenu(@RequestParam String name, @RequestParam String preview,
                          @RequestParam String description, @RequestParam int price, Model model) {
        int popularity = 0;
        Menu menu = new Menu(name, preview, description, price, popularity);
        menuRepository.save(menu);
        return "redirect:/menu";
    }

    @GetMapping("/menu/{id}")
    public String menuDetail(@PathVariable(value = "id") long id, Model model) {

        if (!menuRepository.existsById(id)) {
            return "redirect:/menu";
        }

        model.addAttribute("nameOfPage", "Страва " + id);
        Optional<Menu> menu = menuRepository.findById(id);
        ArrayList<Menu> resultingMenu = new ArrayList<>();
        menu.ifPresent(resultingMenu::add);
        model.addAttribute("menu", resultingMenu);
        return "menu_details";
    }

    @GetMapping("/menu/{id}/edit")
    public String menuEdit(@PathVariable(value = "id") long id, Model model) {

        if (!menuRepository.existsById(id)) {
            return "redirect:/menu";
        }

        model.addAttribute("nameOfPage", "Редагування страви " + id);
        Optional<Menu> menu = menuRepository.findById(id);
        ArrayList<Menu> resultingMenu = new ArrayList<>();
        menu.ifPresent(resultingMenu::add);
        model.addAttribute("menu", resultingMenu);
        return "menu_edit";
    }

    @PostMapping("/menu/{id}/edit")
    public String menuUpdate(@PathVariable(value = "id") long id,
                             @RequestParam String name, @RequestParam String preview,
                             @RequestParam String description, @RequestParam int price, Model model) {
        Menu menu = menuRepository.findById(id).orElseThrow();
        menu.setName(name);
        menu.setPreview(preview);
        menu.setDescription(description);
        menu.setPrice(price);

        menuRepository.save(menu);
        return "redirect:/menu";
    }

    @PostMapping("/menu/{id}/remove")
    public String menuDelete(@PathVariable(value = "id") long id, Model model) {
        Menu menu = menuRepository.findById(id).orElseThrow();
        menuRepository.delete(menu);
        return "redirect:/menu";
    }

    @GetMapping("/filter/menu")
    public String filterMain(Model model) {
        model.addAttribute("title", "Меню");

        Iterable<Menu> menu = menuRepository.findAll();
        model.addAttribute("menu", menu);
        return "menu_main";
    }

    @PostMapping("/filter/menu")
    public String menuFilter(@RequestParam(value = "price_filter", required = false) String checkboxValue,
                             Model model) {
            if(checkboxValue.equals("price_decrease"))
            {
                model.addAttribute("title", "Меню");
                List<Menu> menu = (List<Menu>) menuRepository.findAll();

                model.addAttribute("menu", menuService.sortMenuDecreasePrice(menu));
                return "menu_main";
            }
            else if(checkboxValue.equals("price_increase"))
            {
                model.addAttribute("title", "Меню");
                List<Menu> menu = (List<Menu>) menuRepository.findAll();

                model.addAttribute("menu", menuService.sortMenuIncreasePrice(menu));
                return "menu_main";
            }
            else {
                return "redirect:/menu";
            }
    }

    @GetMapping("/filter/popularity/menu")
    public String filterPopularityMain(Model model) {
        model.addAttribute("title", "Меню");

        Iterable<Menu> menu = menuRepository.findAll();
        model.addAttribute("menu", menu);
        return "menu_main";
    }

    @PostMapping("/filter/popularity/menu")
    public String menuPopularityFilter(@RequestParam(value = "popularity_filter", required = false) String checkboxValue,
                             Model model) {
        if(checkboxValue.equals("popularity_decrease"))
        {
            model.addAttribute("title", "Меню");
            List<Menu> menu = (List<Menu>) menuRepository.findAll();

            model.addAttribute("menu", menuService.sortMenuDecreasePopularity(menu));
            return "menu_main";
        }
        else if(checkboxValue.equals("popularity_increase"))
        {
            model.addAttribute("title", "Меню");
            List<Menu> menu = (List<Menu>) menuRepository.findAll();

            model.addAttribute("menu", menuService.sortMenuIncreasePopularity(menu));
            return "menu_main";
        }
        else {
            return "redirect:/menu";
        }
    }
}
