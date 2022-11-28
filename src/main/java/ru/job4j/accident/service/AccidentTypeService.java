package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeHibernate;
import ru.job4j.accident.repository.AccidentTypeJdbcTemplate;
import ru.job4j.accident.repository.AccidentTypeMem;
import ru.job4j.accident.repository.AccidentTypeRepository;
import java.util.Collection;

@Service
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeMem accidentTypeMem;

    private final AccidentTypeJdbcTemplate accidentTypeJdbcTemplate;
    private final AccidentTypeHibernate accidentTypeHibernate;

    private final AccidentTypeRepository accidentTypeRepository;

    public Collection<AccidentType> findAll() {
        return (Collection<AccidentType>) accidentTypeRepository.findAll();
    }

    public AccidentType finById(int id) {
        return accidentTypeRepository.findById(id);
    }
}
