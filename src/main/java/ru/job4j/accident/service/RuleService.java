package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleJdbcTemplate;
import ru.job4j.accident.repository.RuleMem;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class RuleService {
    private final RuleMem ruleMem;
    private final RuleJdbcTemplate ruleJdbcTemplate;

    public Collection<Rule> findAll() {
        return ruleJdbcTemplate.findAllTemp();
    }

    public List<Rule> finById(int id) {
        return ruleJdbcTemplate.findByIdTemp(id);
    }
}
