package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Collection<AccidentType> findAllTemp() {
        return jdbc.query("SELECT * FROM accident_type",
                new BeanPropertyRowMapper<>(AccidentType.class));
    }

    public AccidentType findByIdTemp(int id) {
        return jdbc.query("SELECT * FROM accident_type WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(AccidentType.class)).stream().findAny().orElse(null);
    }
}
