package ru.nursultanaldibaev.filmorate;

import org.junit.jupiter.api.Test;
import ru.nursultanaldibaev.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FilmValidationTest {

    @Test
    void shouldNotAllowEmptyName() {
        Film film = new Film();
        film.setName("");
        film.setDescription("Описание");
        film.setReleaseDate(LocalDate.of(2000, 1, 1));
        film.setDuration(120);

        assertTrue(film.getName().isEmpty(), "Название фильма не должно быть пустым");
    }
}