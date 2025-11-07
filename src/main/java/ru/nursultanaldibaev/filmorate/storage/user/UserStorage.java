package ru.nursultanaldibaev.filmorate.storage.user;

import ru.nursultanaldibaev.filmorate.model.User;
import java.util.List;

public interface UserStorage {

    /**
     * Добавить нового пользователя
     */
    User addUser(User user);

    /**
     * Обновить данные существующего пользователя
     */
    User updateUser(User user);

    /**
     * Получить пользователя по ID
     */
    User getUserById(Long id);

    /**
     * Получить список всех пользователей
     */
    List<User> getAllUsers();
}
