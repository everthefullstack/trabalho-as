package controller;

import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import service.IsLogged;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class AnunciarController {
 
    public static String getAnunciarPage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                return new ThymeleafTemplateEngine().render(modelAndView(model, "anunciar"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){
            
            return error.toString();
        }

        return "";
    }
}