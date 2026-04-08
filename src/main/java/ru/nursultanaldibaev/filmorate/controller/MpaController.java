package ru.nursultanaldibaev.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import ru.nursultanaldibaev.filmorate.model.Mpa;
import ru.nursultanaldibaev.filmorate.service.MpaService;

@RestController
@RequestMapping("/mpa")
@RequiredArgsConstructor
public class MpaController {
    private final MpaService mpaService;

    @GetMapping
    public List<Mpa> getAll() {
        return mpaService.findAll();
    }

    @GetMapping("/<built-in function id>")
    public ResponseEntity<Mpa> getById(@PathVariable int id) {
        return mpaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
