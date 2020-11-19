package controller;

import static spark.Spark.*;
import java.util.HashMap;
import service.IsLogged;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class ProcurarController {
    
    public static String getProcurarPage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                HashMap<String, Object> model = new HashMap<>();
                return new ThymeleafTemplateEngine().render(modelAndView(model, "procurar"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){

            return error.toString();
        } 

        return "";
    }
}
