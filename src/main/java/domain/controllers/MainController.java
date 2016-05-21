package domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Unidos Contra a Dengue - Aplicação Servidor";
    }

    @RequestMapping("/greeting")
    @ResponseBody
    public Map<String, String> greeting(@RequestParam(value = "name", defaultValue = "server") String name) {

        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        return params;
    }
}
