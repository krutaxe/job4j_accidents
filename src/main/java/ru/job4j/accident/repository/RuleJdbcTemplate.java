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

    public List<Rule> findByIdTemp(int id) {
        return jdbc.query("select r.name from accident a join accident_rule ar\n"
               + "on a.id = ar.accident_id join rule r\n"
               + "on ar.rule_id = r.id where a.id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Rule.class));
    }
}
