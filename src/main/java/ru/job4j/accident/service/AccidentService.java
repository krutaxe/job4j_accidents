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
        return accidentMem.findAll();
    }

    public void create(Accident accident) {
        accidentMem.add(accident);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public void createDb(Accident accident) {
        accidentJdbcTemplate.saveDb(accident);
    }
}
