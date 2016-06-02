package domain.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.entities.User;
import domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper mapper;

    public UserController() {
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String fetch() {
        try {
            return mapper.writeValueAsString(userRepository.findAll());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String insert(String email, String name, String password, Byte inspector, Byte score) {
        try {
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setInspector(inspector);
            user.setScore(score);
            return mapper.writeValueAsString(userRepository.save(user));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable("user") Integer userId, String email, String name, String password, Byte inspector, Byte score) {
        try {
            User user = userRepository.findOne(userId);
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setInspector(inspector);
            user.setScore(score);
            return mapper.writeValueAsString(userRepository.save(user));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("user") Integer userId) {
        try {
            userRepository.delete(new User(userId));
            return "User " + userId + " succesfully deleted!";
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(String email, String password) {
        try {
            return mapper.writeValueAsString(userRepository.findByEmailAndPassword(email, password));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/user/ranking", method = RequestMethod.GET)
    @ResponseBody
    public String ranking() {
        try {
            return mapper.writeValueAsString(userRepository.findAllByOrderByScoreDesc());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

} // class UserController