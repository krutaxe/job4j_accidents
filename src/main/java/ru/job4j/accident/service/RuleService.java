package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleHib;
import ru.job4j.accident.repository.RuleJdbcTemplate;
import ru.job4j.accident.repository.RuleMem;
import ru.job4j.accident.repository.RuleRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class RuleService {
    private final RuleMem ruleMem;
    private final RuleJdbcTemplate ruleJdbcTemplate;
    private final RuleRepository ruleRepository;
    private final RuleHib ruleHib;

    public Collection<Rule> findAll() {
        return (Collection<Rule>) ruleRepository.findAll();
    }

    public List<Rule> finById(String[] ids) {
        List<Rule> ruleList = new ArrayList<>();
        if (ids != null) {
            for (String i : ids) {
                int j = Integer.parseInt(i);
                ruleList.add(ruleRepository.findById(j));
            }
        }
        return ruleList;
    }
}
