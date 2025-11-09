package ru.nursultanaldibaev.filmorate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import ru.nursultanaldibaev.filmorate.model.User;
import ru.nursultanaldibaev.filmorate.storage.user.UserDbStorage;

@JdbcTest
@AutoConfigureTestDatabase
@Import(UserDbStorage.class)
class UserDbStorageTest {
    @Autowired
    private UserDbStorage userDbStorage;

    @Test
    void createAndFindUser() {
        User u = new User();
        u.setEmail("dao@mail.com");
        u.setLogin("dao");
        u.setName("DAO Test");
        u.setBirthday(LocalDate.of(1990,1,1));

        userDbStorage.addUser(u);
        User found = userDbStorage.getUserById(u.getId());
        assertThat(found.getEmail()).isEqualTo("dao@mail.com");
        assertThat(found.getId()).isNotNull();
    }
}
