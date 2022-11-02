package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleMem;
import java.util.List;

@Service
@AllArgsConstructor
public class RuleService {
    private final RuleMem ruleMem;

    public List<Rule> findAll() {
        return ruleMem.findAll();
    }

    public List<Rule> finByIds(String[] ids, List<Rule> rules) {
        return ruleMem.finByIds(ids, rules);
    }
}
