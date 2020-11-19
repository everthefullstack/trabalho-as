package controller;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.UsuarioModel;
import service.IsLogged;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import repository.MeuPerfilRepository;

public class MeuPerfilController {

    public static String getMeuPerfilPage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                ArrayList lista = new MeuPerfilRepository().selectInfosPerfil(req.cookie("usuario"));
                
                UsuarioModel usuario = new UsuarioModel();
                usuario.setNome(lista.get(0).toString());       
                usuario.setCpf(lista.get(1).toString());
                usuario.setTelefone(lista.get(2).toString());
                usuario.setLogin(lista.get(3).toString());

                model.put("usuario", usuario);

                return new ThymeleafTemplateEngine().render(modelAndView(model, "meu_perfil"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){
            
            return error.toString();
        }

        return "";
    }

    public static String editPerfil(Request req, Response res){

        try{
            Boolean edit = false;
            MeuPerfilRepository meuPerfilRepository = new MeuPerfilRepository();

            UsuarioModel perfilInfo = new UsuarioModel();
            perfilInfo.setNome(req.queryParams("nome").toString());
            perfilInfo.setCpf(req.queryParams("cpf").toString());
            perfilInfo.setTelefone(req.queryParams("telefone").toString());
            perfilInfo.setLogin(req.queryParams("login").toString());
            perfilInfo.setSenha(req.queryParams("senha").toString());

            edit = meuPerfilRepository.updatePerfil(perfilInfo);

            if(edit == false){

                res.redirect("/");

            } else {
                res.redirect("/meu_perfil");
            }

        } catch(Exception error){

            return error.toString();
        }

        return "";        
    }
}
