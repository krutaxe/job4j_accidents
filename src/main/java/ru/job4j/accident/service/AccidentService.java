package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;

    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }

    public void add(Accident accident) {
        accidentMem.add(accident);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }
}
