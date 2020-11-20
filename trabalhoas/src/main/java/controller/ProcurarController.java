package controller;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import model.PropostaModel;
import repository.ProcurarRepository;
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

    public static String selectPropostas(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                ProcurarRepository procurarRepository = new ProcurarRepository();
                HashMap<String, Object> model = new HashMap<>();
                ArrayList<ArrayList> propostas = procurarRepository.selectPropostas(req.queryParams("procurar").toString());

                for (ArrayList proposta : propostas) {
                    
                    PropostaModel propostaInfo = new PropostaModel();
                    propostaInfo.setPkcodproposta(Integer.parseInt((String) proposta.get(0)));
                    propostaInfo.setTitulo(proposta.get(1).toString());
                    propostaInfo.setDescricao(proposta.get(2).toString());
                    propostaInfo.setTipo(proposta.get(3).toString());
                    //propostaInfo.setDataHora(proposta.get(4));
                    model.put("proposta" + propostaInfo.getPkcodproposta(), propostaInfo);
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
}
