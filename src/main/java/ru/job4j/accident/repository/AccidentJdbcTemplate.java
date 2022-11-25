package ru.job4j.accident.repository;


import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Accident saveTemp(String[] ids, Accident accident) {
        String query = "INSERT INTO accident (name, text, address, accident_type_id)"
                        + "VALUES (?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps; }, keyHolder);

        accident.setId((Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
        insertRule(ids, accident);
        return accident;
    }

    public void insertRule(String[] ids, Accident accident) {
        if (ids != null) {
            for (String i : ids) {
                int j = Integer.parseInt(i);
                jdbc.update("INSERT INTO accident_rule VALUES (?, ?)",
                        accident.getId(),
                        j);
            }
        }
    }

    public List<Accident> findAllTemp() {
        return jdbc.query("SELECT * FROM accident ORDER BY id",
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
                    accident.setRules(jdbc.query("SELECT r.name FROM accident a "
                                    + "JOIN accident_rule ar\n"
                                    + "ON a.id = ar.accident_id JOIN rule r\n"
                                    + "ON ar.rule_id = r.id WHERE a.id=?",
                            new Object[]{rs.getInt("id")},
                            new BeanPropertyRowMapper<>(Rule.class)));
                    return accident;
                });
    }

    public void updateTemp(String[] ids, Accident accident) {
        jdbc.update("UPDATE accident SET name=?, text=?, "
                        + "address=?, accident_type_id=? WHERE id=?",
                accident.getName(), accident.getText(),
                accident.getAddress(), accident.getType().getId(),
                accident.getId());
        jdbc.update("DELETE FROM accident_rule WHERE accident_id=?",
                accident.getId());
        insertRule(ids, accident);
    }

    public Accident findByIdTemp(int id) {
        return jdbc.query("SELECT * FROM accident WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Accident.class)).stream().findAny().orElse(null);
    }
}