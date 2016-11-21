package domain.controllers;

import domain.entities.User;
import domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Iterable<User> index() {
        return userService.fetchAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User store(String email, String name, String password, String inspector, String score) {
        return userService.insert(email, name, password, Byte.parseByte(inspector), Byte.parseByte(score));
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.GET)
    public User show(@PathVariable("user") Integer userId) {
        return userService.fetchOne(userId);
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.PUT)
    public User update(@PathVariable("user") Integer userId, String email, String name, String password,
                       Byte inspector, Byte score) {

        return userService.update(userId, email, name, password, inspector, score);
    }
}