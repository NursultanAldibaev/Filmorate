package ru.nursultanaldibaev.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public class User {
    private Long id;

    @Email(message = "Некорректный формат email")
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @NotBlank(message = "Логин не может быть пустым")
    private String login;

    private String name;

    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthday;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }
}
