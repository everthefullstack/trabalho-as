package controller;

import static spark.Spark.*;
import java.util.HashMap;
import service.IsLogged;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class SobreController {
    
    public static String getSobrePage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                HashMap<String, Object> model = new HashMap<>();
                return new ThymeleafTemplateEngine().render(modelAndView(model, "sobre"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){

            return error.toString();
        } 

        return "";
    }
}
