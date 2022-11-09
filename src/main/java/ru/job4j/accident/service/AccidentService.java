package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;

    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public Collection<Accident> findAll() {
        return accidentJdbcTemplate.findAllTemp();
    }

    public void create(Accident accident) {
        accidentJdbcTemplate.saveTemp(accident);
    }

    public void update(Accident accident) {
        accidentJdbcTemplate.updateTemp(accident);
    }

    public Accident findById(int id) {
        return accidentJdbcTemplate.findByIdTemp(id);
    }
}
