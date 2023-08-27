package ru.basanov.notification.rest;

import org.springframework.web.bind.annotation.*;
import ru.basanov.notification.exception.UserException;
import ru.basanov.notification.model.User;
import ru.basanov.notification.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    Iterable<User> all() {
        return userService.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return userService.save(newUser);
    }

    @GetMapping("/user/{id}")
    User one(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(()-> new UserException(id));
    }

    @PutMapping("/user/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return userService.findById(newUser.getId())
                .map(user -> {
                    user.setId(newUser.getId());
                    user.setLastName(newUser.getLastName());
                    user.setFirstName(newUser.getFirstName());
                    user.setMiddleName(newUser.getLastName());
                    return userService.save(user);
                })
                .orElseGet(()-> {
                    newUser.setId(id);
                    return userService.save(newUser);
                });
    }
}
