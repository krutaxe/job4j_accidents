package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.AccidentRepository;
import java.util.List;

@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;

    private final AccidentHibernate accidentHibernate;

    private final AccidentRepository accidentRepository;

    public List<Accident> findAll() {
        return accidentRepository.findByOrderById();
    }

    public void create(Accident accident) {
        accidentRepository.save(accident);
    }

    public void update(int id, Accident accident) {
        accident.setId(id);
        accidentRepository.save(accident);
    }

    public Accident findById(int id) {
        return accidentRepository.findById(id);
    }
}
