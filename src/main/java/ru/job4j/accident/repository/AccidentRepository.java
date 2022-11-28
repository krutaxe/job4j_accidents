package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    List<Accident> findByOrderById();

    Accident findById(int id);
}
