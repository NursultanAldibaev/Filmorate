package ru.nursultanaldibaev.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import ru.nursultanaldibaev.filmorate.model.Genre;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final JdbcTemplate jdbcTemplate;

    public List<Genre> findAll() {
        String sql = "SELECT id, name FROM genres ORDER BY id";
        return jdbcTemplate.query(sql, (rs, rn) -> {
            Genre g = new Genre();
            g.setId(rs.getInt("id"));
            g.setName(rs.getString("name"));
            return g;
        });
    }

    public Optional<Genre> findById(int id) {
        String sql = "SELECT id, name FROM genres WHERE id = ?";
        return jdbcTemplate.query(sql, (rs, rn) -> {
            Genre g = new Genre();
            g.setId(rs.getInt("id"));
            g.setName(rs.getString("name"));
            return g;
        }, id).stream().findFirst();
    }
}
