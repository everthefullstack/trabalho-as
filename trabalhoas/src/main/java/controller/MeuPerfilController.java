package controller;

import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import model.UsuarioModel;
import service.IsLogged;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class MeuPerfilController {

    public static String getMeuPerfilPage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                UsuarioModel usuario = new UsuarioModel(1,"b", "b", "c", "d");
                model.put("nome", usuario.getNome());
                return new ThymeleafTemplateEngine().render(modelAndView(model, "meu_perfil"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){
            
            return error.toString();
        }

        return "";
    }
}
