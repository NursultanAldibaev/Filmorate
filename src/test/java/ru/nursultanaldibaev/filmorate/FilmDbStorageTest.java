package ru.nursultanaldibaev.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Set;

import ru.nursultanaldibaev.filmorate.model.Film;
import ru.nursultanaldibaev.filmorate.model.Mpa;
import ru.nursultanaldibaev.filmorate.model.Genre;
import ru.nursultanaldibaev.filmorate.storage.film.FilmDbStorage;

@JdbcTest
@AutoConfigureTestDatabase
@Import(FilmDbStorage.class)
class FilmDbStorageTest {
    @Autowired
    private FilmDbStorage filmDbStorage;

    @Test
    void createFindWithGenres() {
        Film f = new Film();
        f.setName("Interstellar");
        f.setDescription("Space epic");
        f.setReleaseDate(LocalDate.of(2014,11,7));
        f.setDuration(169);
        Mpa m = new Mpa(); m.setId(3); m.setName("PG-13");
        f.setMpa(m);
        Genre g1 = new Genre(); g1.setId(1); g1.setName("Комедия");
        Genre g2 = new Genre(); g2.setId(2); g2.setName("Драма");
        f.setGenres(Set.of(g1, g2));

        filmDbStorage.addFilm(f);
        Film found = filmDbStorage.getFilmById(f.getId());

        assertThat(found.getId()).isNotNull();
        assertThat(found.getMpa().getId()).isEqualTo(3);
        assertThat(found.getGenres()).isNotNull();
        assertThat(found.getGenres().size()).isGreaterThanOrEqualTo(2);
    }
}
