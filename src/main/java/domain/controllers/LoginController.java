package domain.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    private ObjectMapper mapper;

    public LoginController() {
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String index(String email, String password) {
        try {
            return mapper.writeValueAsString(userService.login(email, password));
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
