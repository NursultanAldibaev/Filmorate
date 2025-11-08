package ru.nursultanaldibaev.filmorate.service;

import org.springframework.stereotype.Service;
import ru.nursultanaldibaev.filmorate.model.Film;
import ru.nursultanaldibaev.filmorate.storage.film.FilmStorage;
import java.util.List;

@Service
public class FilmService {
    private final FilmStorage filmStorage;

    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Film addFilm(Film film) {
        return filmStorage.addFilm(film);
    }

    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public Film getFilmById(Long id) {
        return filmStorage.getFilmById(id);
    }
}
