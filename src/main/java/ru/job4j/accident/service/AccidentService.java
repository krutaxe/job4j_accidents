package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.List;

@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;
    private final AccidentJdbcTemplate accidentJdbcTemplate;
    private final AccidentHibernate accidentHibernate;

    public List<Accident> findAllHib() {
        return accidentHibernate.findAllHib();
    }

    public void create(String[] ids, Accident accident) {
        accidentHibernate.saveHib(ids, accident);
    }

    public void update(String[] ids, Accident accident) {
        accidentHibernate.updateHib(ids, accident);
    }

    public Accident findById(int id) {
        return accidentHibernate.findByIdHib(id);
    }
}
