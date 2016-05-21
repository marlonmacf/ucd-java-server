package domain.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.entities.User;
import domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * A class to test interactions with the database using the UserRepository class.
 *
 * @author mandrel
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        List<User> users = null;
        try {
            users = userRepository.findAll();
        } catch (Exception ex) {
            return "Error finding the users: " + ex.toString();
        }

        // Object to JSON in String
        ObjectMapper mapper = new ObjectMapper();
        String retorno = null;
        try {
            retorno = mapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    /**
     * /create  --> Create a new user and save it in the database.
     *
     * @param name      User's name
     * @param email     User's email
     * @param password  User's password
     * @param inspector User's inspector
     * @param score     User's score
     * @return A string describing if the user is succesfully created or not.
     */
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(String email, String name, String password, Boolean inspector, Byte score) {
        User user = null;
        try {
            user = new User(name, email, password, inspector, score);
            userRepository.save(user);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    /**
     * /delete  --> Delete the user having the passed id.
     *
     * @param id The id of the user to delete
     * @return A string describing if the user is succesfully deleted or not.
     */
    @RequestMapping(value = "/user/destroy", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(Integer id) {
        try {
            User user = new User(id);
            userRepository.delete(user);
        } catch (Exception ex) {
            return "Error deleting the user:" + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * /get-by-email  --> Return the id for the user having the passed email.
     *
     * @param email The email to search in the database.
     * @return The user id or a message error if the user is not found.
     */
    @RequestMapping(value = "/user/get-by-email", method = RequestMethod.GET)
    @ResponseBody
    public String getByEmail(String email) {
        String userId;
        try {
            User user = userRepository.findByEmail(email);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    /**
     * /update  --> Update the email and the name for the user in the database
     * having the passed id.
     *
     * @param id    The id for the user to update.
     * @param email The new email.
     * @param name  The new name.
     * @return A string describing if the user is succesfully updated or not.
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    @ResponseBody
    public String updateUser(long id, String email, String name) {
        try {
            User user = userRepository.findOne(id);
            user.setEmail(email);
            user.setName(name);
            userRepository.save(user);
        } catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

} // class UserController