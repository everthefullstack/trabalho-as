package trabalhoas;

import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class App {
    public static void main(String[] args) {
        
        Map<String, Object> model = new HashMap<>();

        staticFileLocation("/static");
        path("/login", () -> {
            get("/", (req, res) -> new ThymeleafTemplateEngine().render(modelAndView(model, "login")));
            post("/login", (req, res) -> new ThymeleafTemplateEngine().render(modelAndView(model, "login")));
        });
        //DatabaseConnection database = new DatabaseConnection("localhost","1521", "xe", "trabalhoas", "trabalhoas");
        //http://localhost:4567/login
        
        
    }
}