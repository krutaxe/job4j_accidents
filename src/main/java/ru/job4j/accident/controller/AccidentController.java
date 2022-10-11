package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
@ThreadSafe
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping("/accidents")
    public String accidents(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "accidents";
    }

    @GetMapping("/addAccident")
    public String viewCreateAccident() {
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidentService.add(accident);
        return "redirect:/index";
    }

    @GetMapping("/updateAccident")
    public String viewUpdateAccident() {
        return "editAccident";
    }

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/accidents";
    }
}
