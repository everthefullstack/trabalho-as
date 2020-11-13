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

    public static String createCadastro(Request req, Response res){

        try{
            HashMap<String, String> cadastroInfo = new HashMap<>();
            cadastroInfo.put("nome", req.queryParams("login"));
            cadastroInfo.put("cpf", req.queryParams("senha"));
            cadastroInfo.put("telefone", req.queryParams("telefone"));
            cadastroInfo.put("login", req.queryParams("login"));
            cadastroInfo.put("senha", req.queryParams("senha"));

        } catch(Exception error){

            return error.toString();
        }

        return "";        
    }
}
