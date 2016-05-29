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
    public String index() {
        try {
            return mapper.writeValueAsString(userRepository.findAll());
        } catch (Exception ex) {
            return "Error finding the users: " + ex.toString();
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String store(String email, String name, String password, Boolean inspector, Byte score) {
        try {
            User user = new User(name, email, password, inspector, score);
            userRepository.save(user);
            return "User succesfully created! (id = " + user.getId() + ")";
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
    }

    @RequestMapping(value = "/user/{user]", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@PathVariable("user") Integer userId, String email, String name, String password, Boolean inspector, Byte score) {
        try {
            User user = userRepository.findOne(Long.valueOf(userId));
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setInspector(inspector);
            user.setScore(score);
            userRepository.save(user);
        } catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.DELETE)
    @ResponseBody
    public String destroy(@PathVariable("user") Integer userId) {
        try {
            User user = new User(userId);
            userRepository.delete(user);
            return "User succesfully deleted!";
        } catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(String email, String password) {
        try {
            return mapper.writeValueAsString(userRepository.findByEmailAndPassword(email, password));
        } catch (Exception ex) {
            return "User not found - " + ex.toString();
        }
    }

} // class UserController