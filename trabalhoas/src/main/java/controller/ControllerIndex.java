package controller;

import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class ControllerIndex {
    
    public static String getIndexPage(Request req, Response res) {

        Map<String, Object> model = new HashMap<>();
        return new ThymeleafTemplateEngine().render(modelAndView(model, "index"));
    }
}
