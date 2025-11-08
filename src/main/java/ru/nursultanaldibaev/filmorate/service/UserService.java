package ru.nursultanaldibaev.filmorate.service;

import org.springframework.stereotype.Service;
import ru.nursultanaldibaev.filmorate.model.User;
import ru.nursultanaldibaev.filmorate.storage.user.UserStorage;
import java.util.List;

@Service
public class UserService {
    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User addUser(User user) {
        return userStorage.addUser(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public User getUserById(Long id) {
        return userStorage.getUserById(id);
    }
}
