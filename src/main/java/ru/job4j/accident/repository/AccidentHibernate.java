package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final SessionFactory sf;

    public void save(String[] ids, Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            insertRule(ids, accident);
            session.save(accident);
            session.getTransaction().commit();
        }
    }

    public List<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "SELECT DISTINCT a FROM  Accident a "
                           + "LEFT JOIN FETCH a.rules ORDER BY a.id", Accident.class).list();
        }
    }

    public List<Rule>  insertRule(String[] ids, Accident accident) {
        List<Rule> rules = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            if (ids != null) {
                for (String i : ids) {
                    int j = Integer.parseInt(i);
                    rules.add(session.get(Rule.class, j));
                }
            }
            accident.setRules(rules);
            session.getTransaction().commit();
        }
        return rules;
    }

    public void update(String[] ids, Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Accident accidentUpdate = session.get(Accident.class, accident.getId());
            accidentUpdate.setName(accident.getName());
            accidentUpdate.setText(accident.getText());
            accidentUpdate.setAddress(accident.getAddress());
            accidentUpdate.setType(accident.getType());
            accidentUpdate.setRules(insertRule(ids, accident));
            session.merge(accidentUpdate);
            session.getTransaction().commit();
        }
    }

    public Accident findById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Accident.class, id);
        }
    }
}
