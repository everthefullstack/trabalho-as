package trabalhoas;

import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        Map map = new HashMap();
        map.put("name", "Sam");

        // hello.html file is in resources/templates directory
        get("/hello", (req, res) -> new ModelAndView(map, "hello"), new ThymeleafTemplateEngine());
    }
}