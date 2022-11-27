package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@ThreadSafe
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("/formCreateAccident")
    public String viewCreateAccident(Model model) {
        Collection<AccidentType> types = accidentTypeService.findAll();
        Collection<Rule> rules = ruleService.findAll();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "createAccident";
    }

    @PostMapping("/createAccident")
    public String add(@ModelAttribute Accident accident,
                      @RequestParam("type.id") int typeId,
                      HttpServletRequest request) {
        AccidentType type = accidentTypeService.finById(typeId);
        String[] ids = request.getParameterValues("rIds");
        accident.setType(type);
        accidentService.create(ids, accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam(value = "id") int id, Model model) {
        Collection<AccidentType> types = accidentTypeService.findAll();
        Collection<Rule> rules = ruleService.findAll();
        model.addAttribute("acc", accidentService.findById(id));
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam("type.id") int id,
                       HttpServletRequest request) {
        AccidentType type = accidentTypeService.finById(id);
        String[] ids = request.getParameterValues("rIds");
        accident.setType(type);
        accidentService.update(ids, accident);
        return "redirect:/index";
    }
}
