package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Accident saveTemp(String[] ids, Accident accident) {
        jdbc.update("insert into accident (name, text, address, accident_type_id) "
                        + "values (?, ?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId());
        return accident;
    }

    public List<Integer> getIds(String[] ids) {
        List<Integer> integerList = new ArrayList<>();
        for (String i : ids) {
            integerList.add(Integer.parseInt(i));
        }
        return integerList;
    }

    public List<Accident> findAllTemp() {
        return jdbc.query("select * from accident ORDER BY id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(jdbc.query("SELECT * FROM accident_type WHERE id=?",
                                    new Object[]{rs.getInt("accident_type_id")},
                                    new BeanPropertyRowMapper<>(AccidentType.class))
                            .stream().findAny().orElse(null));
                    accident.setRules(jdbc.query("select r.name from accident a "
                                    + "join accident_rule ar\n"
                                    + "on a.id = ar.accident_id join rule r\n"
                                    + "on ar.rule_id = r.id where a.id=?",
                            new Object[]{rs.getInt("id")},
                            new BeanPropertyRowMapper<>(Rule.class)));
                    return accident;
                });
    }

    public void updateTemp(Accident accident) {
        jdbc.update("UPDATE accident SET name=?, text=?, "
                        + "address=?, accident_type_id=? WHERE id=?",
                accident.getName(), accident.getText(),
                accident.getAddress(), accident.getType().getId(),
                accident.getId());
    }

    public Accident findByIdTemp(int id) {
        return jdbc.query("SELECT * FROM accident WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Accident.class)).stream().findAny().orElse(null);
    }
}