package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class AccidentMem {
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(3);

    public AccidentMem() {
        accidents.put(1, new Accident(1, "Превышение скорости",
                "Превышение на 30 км/ч", "г.Москва, ул.Ленина 18",
                new AccidentType(1, "Две машины")));
        accidents.put(2, new Accident(2, "Обгон",
                "Обгон при знаке ОБГОН ЗАПРЕЩЁН", "г.Москва, ул Гагарина 40",
                new AccidentType(2, "Машина и мотоцикл")));
        accidents.put(3, new Accident(3, "Парковка",
                "Парковка в запрещенном месте", "г.Москва, парк Победы",
                new AccidentType(3, "Пешиход и машина")));
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void add(Accident accident) {
        accident.setId(ids.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }
}
