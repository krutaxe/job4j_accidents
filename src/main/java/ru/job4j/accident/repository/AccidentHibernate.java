package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    private final SessionFactory sf;

    public void saveHib(String[] ids, Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            insertRule(ids, accident);
            session.save(accident);
            session.getTransaction().commit();
        }
    }

    public List<Accident> findAllHib() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM  Accident order by id", Accident.class).list();
        }
    }

    public void insertRule(String[] ids, Accident accident) {
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
    }

    public void updateHib(String[] ids, Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Accident SET name = :nameParam, "
                    +  "text = :textParam, address = :addressParam, type = :typeParam "
                    + "WHERE id = : idParam");
            query.setParameter("nameParam", accident.getName());
            query.setParameter("textParam", accident.getText());
            query.setParameter("addressParam", accident.getAddress());
            query.setParameter("typeParam", accident.getType());
            query.setParameter("idParam", accident.getId());
            insertRule(ids, accident);
            session.update(accident);
            session.getTransaction().commit();
        }
    }

    public Accident findByIdHib(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Accident.class, id);
        }
    }
}
