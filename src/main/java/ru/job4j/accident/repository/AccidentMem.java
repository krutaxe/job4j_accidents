package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@ThreadSafe
public class AccidentMem {
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(1, "Превышение скорости",
                "Превышение на 30 км/ч", "г.Москва, ул.Ленина 18"));
        accidents.put(2, new Accident(2, "Обгон",
                "Обгон при знаке ОБГОН ЗАПРЕЩЁН", "г.Москва, ул Гагарина 40"));
        accidents.put(3, new Accident(3, "Парковка",
                "Парковка в запрещенном месте", "г.Москва, парк Победы"));
    }

    public Collection<Accident> findAll() {
        System.out.println(accidents.values());
        return accidents.values();
    }
}
