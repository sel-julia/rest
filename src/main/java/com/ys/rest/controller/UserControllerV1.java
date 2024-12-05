package com.ys.rest.controller;

import com.ys.rest.exception.UserNotFoundException;
import com.ys.rest.model.User;
import com.ys.rest.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserControllerV1 {

    private static final String USER_NOT_FOUND_ERROR_MSG = "User with id %s is not found";


    UserService userService;

    public UserControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws BadRequestException {
        if (userService.findByName(user.getName()).isPresent())
            throw new BadRequestException("User with name " + user.getName() + " already exists. Please choose another name and try again.");

        User createdUser = userService.save(user);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR_MSG, id)));

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        User userToUpdate = userService
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR_MSG, id)));

        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        userService.save(userToUpdate);

        return ResponseEntity.ok(userToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        User user = userService
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR_MSG, id)));

        userService.delete(user);

        return ResponseEntity.noContent().build();
    }

}
