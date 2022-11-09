package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeJdbcTemplate;
import ru.job4j.accident.repository.AccidentTypeMem;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeMem accidentTypeMem;

    private final AccidentTypeJdbcTemplate accidentTypeJdbcTemplate;

    public Collection<AccidentType> findAll() {
        return accidentTypeJdbcTemplate.findAllTemp();
    }

    public AccidentType finById(int id) {
        return accidentTypeJdbcTemplate.findByIdTemp(id);
    }
}
