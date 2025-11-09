package ru.nursultanaldibaev.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import ru.nursultanaldibaev.filmorate.model.Mpa;

@Service
@RequiredArgsConstructor
public class MpaService {
    private final JdbcTemplate jdbcTemplate;

    public List<Mpa> findAll() {
        String sql = "SELECT id, name FROM mpa ORDER BY id";
        return jdbcTemplate.query(sql, (rs, rn) -> {
            Mpa m = new Mpa();
            m.setId(rs.getInt("id"));
            m.setName(rs.getString("name"));
            return m;
        });
    }

    public Optional<Mpa> findById(int id) {
        String sql = "SELECT id, name FROM mpa WHERE id = ?";
        return jdbcTemplate.query(sql, (rs, rn) -> {
            Mpa m = new Mpa();
            m.setId(rs.getInt("id"));
            m.setName(rs.getString("name"));
            return m;
        }, id).stream().findFirst();
    }
}
