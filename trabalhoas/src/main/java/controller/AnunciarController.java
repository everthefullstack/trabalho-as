package controller;

import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import model.PropostaModel;
import repository.AnunciarRepository;
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

    public static String createAnunciar(Request req, Response res){

        try{
            Boolean cadastro = false;
            AnunciarRepository anunciarRepository = new AnunciarRepository();

            PropostaModel anuncioInfo = new PropostaModel();
            anuncioInfo.setTitulo(req.queryParams("titulo").toString());
            anuncioInfo.setDescricao(req.queryParams("descricao").toString());
            anuncioInfo.setTipo(req.queryParams("tipo").toString());
            anuncioInfo.setFkcodusuario(anunciarRepository.selectFkCodUsuario(req.cookie("usuario")));

            cadastro = anunciarRepository.insertAnuncio(anuncioInfo);
  
            if(cadastro == false){
                res.redirect("/");

            } else {
                res.redirect("/meus_anuncios");
            }

        } catch(Exception error){

            return error.toString();
        }

        return "";        
    }
}