package ua.yarynych.demoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yarynych.demoapi.model.Worker;
import ua.yarynych.demoapi.repository.WorkerRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class WorkerController {

    @Autowired
    private WorkerRepository workerRepository;


    @GetMapping("/personal")
    public String personalMain(Model model) {
        model.addAttribute("title", "Наші співробітники");
        Iterable<Worker> worker = workerRepository.findAll();
        model.addAttribute("worker", worker);
        return "workers_main";
    }

    @GetMapping("/personal/edit")
    public String personalEdit(Model model) {
        model.addAttribute("title", "Наші співробітники адмін контроль");
        Iterable<Worker> worker = workerRepository.findAll();
        model.addAttribute("worker", worker);
        return "workers_edit";
    }

    @GetMapping("/personal/add")
    public String personalAdd(Model model) {
        model.addAttribute("title", "Додати працівника");
        return "worker_add";
    }

    @PostMapping("/personal/add")
    public String addPersonal(@RequestParam String name, @RequestParam String first_name,
                          @RequestParam String position, Model model) {
        Worker worker = new Worker(name, first_name, position);
        workerRepository.save(worker);
        return "redirect:/personal/edit";
    }

    @GetMapping("/personal/edit/{id}")
    public String personalIdEdit(@PathVariable(value = "id") long id, Model model) {

        if(!workerRepository.existsById(id)) {
            return "redirect:/menu";
        }

        model.addAttribute("nameOfPage", "Редагування працівника" + id);
        Optional<Worker> worker = workerRepository.findById(id);
        ArrayList<Worker> resultingWorker = new ArrayList<>();
        worker.ifPresent(resultingWorker::add);
        model.addAttribute("worker", resultingWorker);
        return "worker_id_edit";
    }

    @PostMapping("/personal/edit/{id}")
    public String personalUpdate(@PathVariable(value = "id") long id,
                             @RequestParam String name, @RequestParam String first_name,
                             @RequestParam String position, Model model) {
        Worker worker = workerRepository.findById(id).orElseThrow();
        worker.setName(name);
        worker.setFirs_name(first_name);
        worker.setPosition(position);

        workerRepository.save(worker);

        return "redirect:/personal/edit";
    }

    @PostMapping("/personal/remove/{id}")
    public String personalDelete(@PathVariable(value = "id") long id, Model model) {
        Worker worker = workerRepository.findById(id).orElseThrow();
        workerRepository.delete(worker);
        return "redirect:/personal/edit";
    }
}
