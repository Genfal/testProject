package ru.project.test.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.project.test.v2.dto.UserDto;
import ru.project.test.v2.model.User;
import ru.project.test.v2.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @author TorstendasTost
 * @since 24.10.2022
 */
@RestController
@RequestMapping("/project/test")
public class Controller {

    private final UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PostMapping("/all")
    public List<UserDto> createUsers(@RequestBody List<UserDto> usersDto) {
        return userService.saveUsers(usersDto);
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUserById(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping
    public List<User> testQuery1(@RequestParam String country) {
        return userService.testQuery1(country);
    }

    @GetMapping("/testQuery2")
    public List<User> testQuery2() {
        return userService.testQuery2();
    }

    @GetMapping("/testQuery3")
    public List testQuery() {
        return userService.testQuery3();
    }

    @GetMapping("/testQuerySql")
    public Map<String, Integer> testQuerySql() {
        return userService.testQuerySql();
    }
}
