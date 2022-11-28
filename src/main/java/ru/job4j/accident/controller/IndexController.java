package ru.job4j.accident.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@ThreadSafe
@Controller
public class IndexController {

    private final AccidentService accidentService;

    public IndexController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/index")
    public String index(Model model, @ModelAttribute Accident accident) {
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}