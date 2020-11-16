package controller;

import static spark.Spark.*;
import java.util.HashMap;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import repository.CadastroRepository;

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
            Boolean cadastro = false;
            CadastroRepository cadRepository = new CadastroRepository();
            HashMap<String, String> cadastroInfo = new HashMap<>();
            cadastroInfo.put("nome", req.queryParams("nome"));
            cadastroInfo.put("cpf", req.queryParams("cpf"));
            cadastroInfo.put("telefone", req.queryParams("telefone"));
            cadastroInfo.put("login", req.queryParams("login"));
            cadastroInfo.put("senha", req.queryParams("senha"));

            cadastro = cadRepository.insertCadastro(cadastroInfo);

            if(cadastro == false){
                res.redirect("/cadastro");

            } else {
                res.redirect("/");
            }

        } catch(Exception error){

            return error.toString();
        }

        return "";        
    }
}
