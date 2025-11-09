package ru.nursultanaldibaev.filmorate.storage.user;

import org.springframework.stereotype.Component;
import ru.nursultanaldibaev.filmorate.exception.NotFoundException;
import ru.nursultanaldibaev.filmorate.model.User;
import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage {

    private final Map<Long, User> users = new HashMap<>();
    private long idCounter = 1;

    @Override
    public User addUser(User user) {
        user.setId(idCounter++);
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (!users.containsKey(user.getId())) {
            throw new NotFoundException("Пользователь с ID " + user.getId() + " не найден");
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUserById(Long id) {
        User user = users.get(id);
        if (user == null) throw new NotFoundException("Пользователь с ID " + id + " не найден");
        return user;
    }
}
