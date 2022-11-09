package ru.job4j.accident.repository;


import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Collection<Rule> findAllTemp() {
        return jdbc.query("SELECT * FROM rule",
                new BeanPropertyRowMapper<>(Rule.class));
    }

    public List<Rule> findByIdTemp(String[] ids) {
        List<Rule> list = new ArrayList<>();
        if (ids != null) {
            for (String id : ids) {
                list.add(jdbc.query("SELECT * FROM rule WHERE id=?", new Object[]{ids},
                        new BeanPropertyRowMapper<>(Rule.class)).stream().findAny().orElse(null));
            }
        } else {
            list.add(new Rule(0, "Статья отсутствует"));
        }
        return list;
    }
}
