package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentTypeMem {
    private Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    public AccidentTypeMem() {
        types.put(1, new AccidentType(1, "Две машины"));
        types.put(2, new AccidentType(2, "Машина и пешиход"));
        types.put(3, new AccidentType(3, "Машина и велостпед"));
    }

    public Collection<AccidentType> findAll() {
        return types.values();
    }

    public AccidentType findById(int id) {
        return types.get(id);
    }
}
