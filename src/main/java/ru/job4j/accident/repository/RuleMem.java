package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class RuleMem {
    private final List<Rule> rules = new ArrayList<>();

    public List<Rule> findAll() {
        return rules;
    }

}
