package controller;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.PropostaModel;
import model.UsuarioxPropostaModel;
import repository.MeusAnunciosRepository;
import service.IsLogged;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class MeusAnunciosController {

    public static String getMeusAnunciosPage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                ArrayList<UsuarioxPropostaModel> propostasModel = new ArrayList<>();
                ArrayList<ArrayList> propostasArray = new MeusAnunciosRepository().selectPropostas(req.cookie("usuario"));
                
                for (ArrayList proposta : propostasArray) {
                    
                    UsuarioxPropostaModel propostaInfo = new UsuarioxPropostaModel();
                    propostaInfo.setPkcodpropostaUm(Integer.parseInt((String) proposta.get(0)));
                    propostaInfo.setTituloUm(proposta.get(1).toString());
                    propostaInfo.setDescricao(proposta.get(2).toString());
                    propostaInfo.setTipo(proposta.get(3).toString());
                    propostaInfo.setNome(proposta.get(4).toString());
                    propostaInfo.setAtivo(proposta.get(5).toString());

                    propostasModel.add(propostaInfo);

                    model.put("proposta", propostasModel);
                }
                
                return new ThymeleafTemplateEngine().render(modelAndView(model, "meus_anuncios"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){

            return error.toString();
        } 

        return "";
    }
    
    public static String getEditarMeusAnunciosPage(Request req, Response res){
        
        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                PropostaModel proposta = new PropostaModel();
                ArrayList propostaArray = new MeusAnunciosRepository().selectProposta(req.params(":pkcodproposta"), req.cookie("usuario"));
                
                proposta.setPkcodproposta(Integer.parseInt(propostaArray.get(0).toString()));
                proposta.setTitulo(propostaArray.get(1).toString());
                proposta.setDescricao(propostaArray.get(2).toString());
                proposta.setTipo(propostaArray.get(3).toString());

                model.put("proposta", proposta);

                return new ThymeleafTemplateEngine().render(modelAndView(model, "editar_anuncio"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){
            
            return error.toString();
        }

        return "";
    }

    public static String editAnuncio(Request req, Response res){

        try{
            Boolean edit = false;
            MeusAnunciosRepository meusAnunciosRepository = new MeusAnunciosRepository();

            PropostaModel propostaInfo = new PropostaModel();
            propostaInfo.setPkcodproposta(Integer.parseInt(req.params(":pkcodproposta").toString()));
            propostaInfo.setTitulo(req.queryParams("titulo").toString());
            propostaInfo.setTipo(req.queryParams("tipo").toString());
            propostaInfo.setDescricao(req.queryParams("descricao").toString());

            edit = meusAnunciosRepository.updateProposta(propostaInfo);

            if(edit == false){

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
