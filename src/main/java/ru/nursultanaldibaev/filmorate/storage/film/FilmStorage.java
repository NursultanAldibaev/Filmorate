package ru.nursultanaldibaev.filmorate.storage.film;

import ru.nursultanaldibaev.filmorate.model.Film;
import java.util.List;

public interface FilmStorage {
    Film addFilm(Film film);
    Film updateFilm(Film film);
    Film getFilmById(Long id);
    List<Film> getAllFilms();
}
