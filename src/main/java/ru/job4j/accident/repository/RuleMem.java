package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class RuleMem {
    private final List<Rule> rules = new ArrayList<>();

    public RuleMem() {
        rules.add(new Rule(0, "Статья. 1"));
        rules.add(new Rule(1, "Статья. 2"));
        rules.add(new Rule(2, "Статья. 3"));
    }

    public List<Rule> findAll() {
        return rules;
    }

    public List<Rule> finByIds(String[] ids, List<Rule> rules) {
        List<Rule> list = new ArrayList<>();
        if (ids != null) {
            for (String id : ids) {
                System.out.println(id);
                list.add(rules.get(Integer.parseInt(id)));
            }
        } else {
            list.add(new Rule(0, "Статья отсутствует"));
        }
        return list;
    }
}
