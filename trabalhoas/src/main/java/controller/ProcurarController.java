package controller;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.PropostaModel;
import model.UsuarioxPropostaModel;
import repository.ProcurarRepository;
import service.IsLogged;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class ProcurarController {
    
    public static String getProcurarAnunciosPage(Request req, Response res) {

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

    public static String selectAnuncios(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                ArrayList<UsuarioxPropostaModel> propostasModel = new ArrayList<>();
                ArrayList<ArrayList> propostasArray = new ProcurarRepository().selectPropostas(req.queryParams("procurar").toString(), req.cookie("usuario"));
                
                for (ArrayList proposta : propostasArray) {
                    
                    UsuarioxPropostaModel propostaInfo = new UsuarioxPropostaModel();
                    propostaInfo.setPkcodpropostaUm(Integer.parseInt((String) proposta.get(0)));
                    propostaInfo.setTituloUm(proposta.get(1).toString());
                    propostaInfo.setDescricao(proposta.get(2).toString());
                    propostaInfo.setTipo(proposta.get(3).toString());
                    propostaInfo.setNome(proposta.get(4).toString());

                    propostasModel.add(propostaInfo);

                    model.put("proposta", propostasModel);
                    
                }
                
                return new ThymeleafTemplateEngine().render(modelAndView(model, "procurar"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){

            return error.toString();
        } 

        return "";
    }

    public static String getAnuncioPage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                PropostaModel proposta = new PropostaModel();
                ArrayList propostaArray = new ProcurarRepository().selectProposta(req.params(":pkcodproposta"), req.cookie("usuario"));
                
                proposta.setPkcodproposta(Integer.parseInt(propostaArray.get(0).toString()));
                proposta.setTitulo(propostaArray.get(1).toString());
                proposta.setDescricao(propostaArray.get(2).toString());
                proposta.setTipo(propostaArray.get(3).toString());

                model.put("proposta", proposta);

                return new ThymeleafTemplateEngine().render(modelAndView(model, "anuncio"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){
            
            return error.toString();
        }

        return "";
    }

    public static String getPropostaAnuncioPage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                return new ThymeleafTemplateEngine().render(modelAndView(model, "proposta"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){
            
            return error.toString();
        }

        return "";
    }

    public static String createPropostaAnuncioPage(Request req, Response res){

        try{
            Boolean cadastro = false;
            String fkcodtbproposta1 = "";
            ProcurarRepository procurarRepository = new ProcurarRepository();

            PropostaModel propostaInfo = new PropostaModel();
            propostaInfo.setTitulo(req.queryParams("titulo").toString());
            propostaInfo.setDescricao(req.queryParams("descricao").toString());
            propostaInfo.setTipo(req.queryParams("tipo").toString());
            propostaInfo.setFkcodusuario(procurarRepository.selectFkCodUsuario(req.cookie("usuario")));
            fkcodtbproposta1 = req.params(":pkcodproposta");

            cadastro = procurarRepository.insertProposta(propostaInfo, fkcodtbproposta1);
  
            if(cadastro == false){
                res.redirect("/");

            } else {
                res.redirect("/propostas_realizadas");
            }

        } catch(Exception error){

            return error.toString();
        }

        return "";        
    }
}
