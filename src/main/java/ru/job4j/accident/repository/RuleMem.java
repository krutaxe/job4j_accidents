package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class RuleMem {
    private final Map<Integer, Rule> rules = new HashMap<>();

    public RuleMem() {
        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }

    public Collection<Rule> findAll() {
        return rules.values();
    }

    public List<Rule> finByIds(String[] ids, List<Rule> rules) {
        List<Rule> list = new ArrayList<>();
        for (String id : ids) {
            list.add(rules.get(Integer.parseInt(id) - 1));
        }
        return list;
    }
}
