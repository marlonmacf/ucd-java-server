package domain.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.entities.User;
import domain.repositories.UserRepository;
import domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private ObjectMapper mapper;

    public UserController() {
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        try {
            return mapper.writeValueAsString(userService.fetchAll());
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    @ResponseBody
    public String create() {
        return "create";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String store(String email, String name, String password, Byte inspector, Byte score) {
        try {
            return mapper.writeValueAsString(userService.insert(email, name, password, inspector, score));
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.GET)
    @ResponseBody
    public String show(@PathVariable("user") Integer userId) {
        try {
            return mapper.writeValueAsString(userService.fetchOne(userId));
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/user/{user}/edit", method = RequestMethod.GET)
    @ResponseBody
    public String edit(@PathVariable("user") Integer userId) {
        return "edit";
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable("user") Integer userId, String email, String name, String password, Byte inspector, Byte score) {
        try {
            return mapper.writeValueAsString(userService.update(userId, email, name, password, inspector, score));
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.DELETE)
    @ResponseBody
    public String destroy(@PathVariable("user") Integer userId) {
        try {
            return mapper.writeValueAsString(userService.delete(userId));
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/user/ranking", method = RequestMethod.GET)
    @ResponseBody
    public String ranking() {
        try {
            return mapper.writeValueAsString(userService.ranking());
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}