package com.ys.rest.controller;

import com.ys.rest.model.User;
import com.ys.rest.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserControllerV1 {

    UserService userService;

    public UserControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.save(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id = " + id + " not found"));

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = userService.findById(id)
                .map(u -> {
                    u.setName(user.getName());
                    u.setAge(user.getAge());
                    return userService.save(u);
                })
                .orElseGet(() -> userService.save(user));

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id = " + id + " not found"));
        userService.delete(user);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
