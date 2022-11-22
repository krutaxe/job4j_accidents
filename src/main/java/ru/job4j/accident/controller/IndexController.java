package ru.job4j.accident.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.RuleService;

@ThreadSafe
@Controller
public class IndexController {

    private final AccidentService accidentService;
    private final RuleService ruleService;

    public IndexController(AccidentService accidentService, RuleService ruleService) {
        this.accidentService = accidentService;
        this.ruleService = ruleService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
