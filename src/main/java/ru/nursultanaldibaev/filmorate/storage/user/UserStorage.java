package ru.nursultanaldibaev.filmorate.storage.user;

import ru.nursultanaldibaev.filmorate.model.User;
import java.util.List;

public interface UserStorage {
    User addUser(User user);
    User updateUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
}
