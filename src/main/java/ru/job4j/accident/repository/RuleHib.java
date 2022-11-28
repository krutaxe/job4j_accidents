package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
public class RuleHib {
    private final SessionFactory sf;

    public List<Rule> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Rule", Rule.class).list();
        }
    }

    public List<Rule> findById(int id) {
        try (Session session = sf.openSession()) {
            Accident accident = session.get(Accident.class, id);
            return accident.getRules();
        }
    }
}
