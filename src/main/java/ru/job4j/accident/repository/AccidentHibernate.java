package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final SessionFactory sf;

    @SneakyThrows
    public Accident saveHb(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    @SneakyThrows
    public List<Accident> getAllHb() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
    }
}
