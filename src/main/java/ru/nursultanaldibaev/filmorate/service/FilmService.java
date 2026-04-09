package ru.nursultanaldibaev.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nursultanaldibaev.filmorate.exception.NotFoundException;
import ru.nursultanaldibaev.filmorate.exception.ValidationException;
import ru.nursultanaldibaev.filmorate.model.Film;
import ru.nursultanaldibaev.filmorate.storage.film.FilmStorage;

import java.util.Comparator;
import java.util.List;

@Service
public class FilmService {

    private final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Film addFilm(Film film) {
        validateFilm(film);
        return filmStorage.addFilm(film);
    }

    public Film updateFilm(Film film) {
        validateFilm(film);
        Film existing = filmStorage.getFilmById(film.getId());
        if (existing == null) {
            throw new NotFoundException("Фильм с id=" + film.getId() + " не найден");
        }
        return filmStorage.updateFilm(film);
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public Film getFilmById(Long id) {
        Film film = filmStorage.getFilmById(id);
        if (film == null) {
            throw new NotFoundException("Фильм с id=" + id + " не найден");
        }
        return film;
    }

    // 🔥 ДОБАВИЛИ ЛАЙК
    public void addLike(Long filmId, Long userId) {
        Film film = getFilmById(filmId);
        film.getLikes().add(userId);
    }

    // 🔥 УДАЛЕНИЕ ЛАЙКА
    public void removeLike(Long filmId, Long userId) {
        Film film = getFilmById(filmId);
        film.getLikes().remove(userId);
    }

    // 🔥 ПОПУЛЯРНЫЕ ФИЛЬМЫ
    public List<Film> getPopularFilms(int count) {
        return filmStorage.getAllFilms().stream()
                .sorted(Comparator.comparingInt((Film f) -> f.getLikes().size()).reversed())
                .limit(count)
                .toList();
    }

    private void validateFilm(Film film) {
        if (film.getName() == null || film.getName().isBlank()) {
            throw new ValidationException("Название фильма не может быть пустым");
        }
        if (film.getDuration() <= 0) {
            throw new ValidationException("Продолжительность фильма должна быть положительной");
        }
    }
}