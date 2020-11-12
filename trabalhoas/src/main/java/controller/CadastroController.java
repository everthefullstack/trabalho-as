package controller;

import static spark.Spark.*;
import java.util.HashMap;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class CadastroController {
    
    public static String getCadastroPage(Request req, Response res) {

        try{
            HashMap<String, Object> model = new HashMap<>();
            return new ThymeleafTemplateEngine().render(modelAndView(model, "cadastro"));
            
        }catch(Exception error){

            return error.toString();
        } 
    }
}
