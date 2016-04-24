package domain.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GreetingController {

    @RequestMapping("/greeting")
    public Map<String, String> greeting(@RequestParam(value = "name", defaultValue = "server") String name) {

        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        return params;
    }
}
