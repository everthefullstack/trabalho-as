package controller;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.UsuarioxPropostaModel;
import repository.PropRecebidasRepository;
import service.IsLogged;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class PropRecebidasController {
    
    public static String getPropRecebidasPage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                ArrayList<UsuarioxPropostaModel> propostasModel = new ArrayList<>();
                ArrayList<ArrayList> propostasArray = new PropRecebidasRepository().selectPropostas(req.cookie("usuario"));
                
                for (ArrayList proposta : propostasArray) {
                    
                    UsuarioxPropostaModel propostaInfo = new UsuarioxPropostaModel();
                    propostaInfo.setPkcodpropostaUm(Integer.parseInt((String) proposta.get(0)));
                    propostaInfo.setTituloUm(proposta.get(1).toString());
                    propostaInfo.setDescricao(proposta.get(2).toString());
                    propostaInfo.setTipo(proposta.get(3).toString());
                    propostaInfo.setNome(proposta.get(4).toString());
                    propostaInfo.setAtivo(proposta.get(5).toString());
                    propostaInfo.setTelefone(proposta.get(6).toString());
                    propostaInfo.setPkcodpropostaDois(Integer.parseInt((String) proposta.get(7)));
                    propostaInfo.setTituloDois((proposta.get(8).toString()));

                    propostasModel.add(propostaInfo);

                    model.put("proposta", propostasModel);
                }
                
                return new ThymeleafTemplateEngine().render(modelAndView(model, "propostas_recebidas"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){

            return error.toString();
        } 

        return "";
    }

    public static String getVisualizarPropRecebidasPage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                UsuarioxPropostaModel propostaModel = new UsuarioxPropostaModel();
                ArrayList propostaArray = new PropRecebidasRepository().selectProposta(req.params(":pkcodproposta"));
                
                propostaModel.setPkcodpropostaUm(Integer.parseInt(propostaArray.get(0).toString()));
                propostaModel.setNome(propostaArray.get(1).toString());
                propostaModel.setTelefone(propostaArray.get(2).toString());
                propostaModel.setTituloUm(propostaArray.get(3).toString());
                propostaModel.setTipo(propostaArray.get(4).toString());
                propostaModel.setDescricao(propostaArray.get(5).toString());
                propostaModel.setAtivo(propostaArray.get(6).toString());

                model.put("proposta", propostaModel);

                return new ThymeleafTemplateEngine().render(modelAndView(model, "visualizar_oferta"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){
            
            return error.toString();
        }

        return "";
    }

    public static String acceptPropRecebidas(Request req, Response res){

        try{
            Boolean cadastro = false;

            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                cadastro = new PropRecebidasRepository().updateProposta(req.params(":pkcodproposta"), Integer.parseInt(req.queryParams("aceite")));

                if(cadastro == false){

                    res.redirect("/propostas_recebidas");
    
                } else {

                    res.redirect("/");
                }
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){

            return error.toString();
        } 

        return "";
    }
}