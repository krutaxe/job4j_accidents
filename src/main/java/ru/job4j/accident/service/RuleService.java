package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleMem;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RuleService {
    private final RuleMem ruleMem;

    public Map<Integer, Rule> findAll() {
        return ruleMem.findAll();
    }

    public List<Rule> finByIds(String[] ids) {
        return ruleMem.finByIds(ids);
    }
}
