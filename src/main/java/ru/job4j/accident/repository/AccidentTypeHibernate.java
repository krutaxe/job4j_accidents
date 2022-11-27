package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate {
    private final SessionFactory sf;

    public List<AccidentType> findAllHib() {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "FROM AccidentType", AccidentType.class).list();
        }
    }

    public AccidentType findByIdHib(int id) {
        try (Session session = sf.openSession()) {
            return session.get(AccidentType.class, id);
        }
    }
}
