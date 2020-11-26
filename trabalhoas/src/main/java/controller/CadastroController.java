package controller;

import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import model.UsuarioModel;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import repository.CadastroRepository;

public class CadastroController {
    
    public static String getCadastroPage(Request req, Response res) {

        try{
            Map<String, Object> model = new HashMap<>();
            return new ThymeleafTemplateEngine().render(modelAndView(model, "cadastro"));
            
        }catch(Exception error){

            return error.toString();
        } 
    }

    public static String createCadastro(Request req, Response res){

        try{
            Boolean cadastro = false;
            CadastroRepository cadRepository = new CadastroRepository();

            UsuarioModel cadastroInfo = new UsuarioModel();
            cadastroInfo.setNome(req.queryParams("nome").toString());
            cadastroInfo.setCpf(req.queryParams("cpf").toString());
            cadastroInfo.setTelefone(req.queryParams("telefone").toString());
            cadastroInfo.setLogin(req.queryParams("login").toString());
            cadastroInfo.setSenha(req.queryParams("senha").toString());

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
