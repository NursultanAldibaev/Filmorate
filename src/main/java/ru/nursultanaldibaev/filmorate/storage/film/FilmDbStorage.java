package ru.nursultanaldibaev.filmorate.storage.film;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.nursultanaldibaev.filmorate.model.Film;
import ru.nursultanaldibaev.filmorate.model.Mpa;
import ru.nursultanaldibaev.filmorate.model.Genre;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

@Repository
@Qualifier("filmDbStorage")
@RequiredArgsConstructor
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Film addFilm(Film film) {
        String sql = "INSERT INTO films (name, description, release_date, duration, mpa_id) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"});
            stmt.setString(1, film.getName());
            stmt.setString(2, film.getDescription());
            stmt.setDate(3, Date.valueOf(film.getReleaseDate()));
            stmt.setInt(4, film.getDuration());
            stmt.setInt(5, film.getMpa().getId());
            return stmt;
        }, keyHolder);

        film.setId(keyHolder.getKey().longValue());
        saveGenres(film.getId(), film.getGenres());
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        String sql = "UPDATE films SET name=?, description=?, release_date=?, duration=?, mpa_id=? WHERE id=?";
        jdbcTemplate.update(sql,
                film.getName(),
                film.getDescription(),
                Date.valueOf(film.getReleaseDate()),
                film.getDuration(),
                film.getMpa().getId(),
                film.getId());
        saveGenres(film.getId(), film.getGenres());
        return film;
    }

    @Override
    public List<Film> getAllFilms() {
        String sql = "SELECT f.*, m.name AS mpa_name FROM films f JOIN mpa m ON f.mpa_id = m.id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Film f = new Film();
            f.setId(rs.getLong("id"));
            f.setName(rs.getString("name"));
            f.setDescription(rs.getString("description"));
            f.setReleaseDate(rs.getDate("release_date").toLocalDate());
            f.setDuration(rs.getInt("duration"));

            Mpa mpa = new Mpa();
            mpa.setId(rs.getInt("mpa_id"));
            mpa.setName(rs.getString("mpa_name"));
            f.setMpa(mpa);
            f.setGenres(loadGenres(f.getId()));
            return f;
        });
    }

    @Override
    public Film getFilmById(Long id) {
        String sql = "SELECT f.*, m.name AS mpa_name FROM films f JOIN mpa m ON f.mpa_id = m.id WHERE f.id = ?";
        Optional<Film> film = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Film f = new Film();
            f.setId(rs.getLong("id"));
            f.setName(rs.getString("name"));
            f.setDescription(rs.getString("description"));
            f.setReleaseDate(rs.getDate("release_date").toLocalDate());
            f.setDuration(rs.getInt("duration"));

            Mpa mpa = new Mpa();
            mpa.setId(rs.getInt("mpa_id"));
            mpa.setName(rs.getString("mpa_name"));
            f.setMpa(mpa);
            f.setGenres(loadGenres(f.getId()));
            return f;
        }, id).stream().findFirst();

        return film.orElseThrow(() -> new RuntimeException("Фильм с ID " + id + " не найден"));
    }


private void saveGenres(long filmId, java.util.Set<Genre> genres) {
    jdbcTemplate.update("DELETE FROM film_genres WHERE film_id = ?", filmId);
    if (genres == null || genres.isEmpty()) return;
    for (Genre g : genres) {
        jdbcTemplate.update("INSERT INTO film_genres (film_id, genre_id) VALUES (?, ?)", filmId, g.getId());
    }
}

private java.util.Set<Genre> loadGenres(long filmId) {
    String sql = "SELECT g.id, g.name FROM film_genres fg JOIN genres g ON fg.genre_id=g.id WHERE fg.film_id=?";
    return new java.util.HashSet<>(jdbcTemplate.query(sql, (rs, rn) -> {
        Genre gr = new Genre();
        gr.setId(rs.getInt("id"));
        gr.setName(rs.getString("name"));
        return gr;
    }, filmId));
}

}
