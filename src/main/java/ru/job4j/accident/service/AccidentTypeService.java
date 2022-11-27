package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeHibernate;
import ru.job4j.accident.repository.AccidentTypeJdbcTemplate;
import ru.job4j.accident.repository.AccidentTypeMem;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeMem accidentTypeMem;

    private final AccidentTypeJdbcTemplate accidentTypeJdbcTemplate;
    private final AccidentTypeHibernate accidentTypeHibernate;

    public Collection<AccidentType> findAll() {
        return accidentTypeHibernate.findAllHib();
    }

    public AccidentType finById(int id) {
        return accidentTypeHibernate.findByIdHib(id);
    }
}
