package ru.nursultanaldibaev.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import java.time.LocalDate;

@Data
public class User {
    private Long id;

    @Email(message = "Некорректный email.")
    @NotBlank(message = "Email не может быть пустым.")
    private String email;

    @NotBlank(message = "Логин не может быть пустым.")
    private String login;

    private String name;

    @NotNull(message = "Дата рождения обязательна.")
    @Past(message = "Дата рождения должна быть в прошлом.")
    private LocalDate birthday;
}
