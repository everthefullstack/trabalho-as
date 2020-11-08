package controller;

import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class IndexController {
    
    public static String getIndexPage(Request req, Response res) {

        try{
            Map<String, Object> model = new HashMap<>();
            return new ThymeleafTemplateEngine().render(modelAndView(model, "index"));

        }catch(Exception error){
            
            return error.toString();
        }
    }
}
