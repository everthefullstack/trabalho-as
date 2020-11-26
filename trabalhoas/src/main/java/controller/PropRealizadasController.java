package controller;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.PropostaModel;
import model.UsuarioxPropostaModel;
import repository.PropRealizadasRepository;
import service.IsLogged;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class PropRealizadasController {

    public static String getPropRealizadasPage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                ArrayList<UsuarioxPropostaModel> propostasModel = new ArrayList<>();
                ArrayList<ArrayList> propostasArray = new PropRealizadasRepository().selectPropostas(req.cookie("usuario"));
                
                for (ArrayList proposta : propostasArray) {
                    
                    UsuarioxPropostaModel propostaInfo = new UsuarioxPropostaModel();
                    propostaInfo.setPkcodpropostaUm(Integer.parseInt((String) proposta.get(0)));
                    propostaInfo.setTituloUm(proposta.get(1).toString());
                    propostaInfo.setDescricao(proposta.get(2).toString());
                    propostaInfo.setTipo(proposta.get(3).toString());
                    propostaInfo.setNome(proposta.get(4).toString());
                    propostaInfo.setAtivo(proposta.get(5).toString());
                    propostaInfo.setPkcodpropostaDois(Integer.parseInt((String) proposta.get(6)));
                    propostaInfo.setTituloDois((proposta.get(7).toString()));

                    propostasModel.add(propostaInfo);

                    model.put("proposta", propostasModel);
                }
                
                return new ThymeleafTemplateEngine().render(modelAndView(model, "propostas_realizadas"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){

            return error.toString();
        } 

        return "";
    }

    public static String getEditarPropRealizadasPage(Request req, Response res) {
       
        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                Map<String, Object> model = new HashMap<>();
                PropostaModel proposta = new PropostaModel();
                ArrayList propostaArray = new PropRealizadasRepository().selectProposta(req.params(":pkcodproposta"), req.cookie("usuario"));
                
                proposta.setPkcodproposta(Integer.parseInt(propostaArray.get(0).toString()));
                proposta.setTitulo(propostaArray.get(1).toString());
                proposta.setDescricao(propostaArray.get(2).toString());
                proposta.setTipo(propostaArray.get(3).toString());

                model.put("proposta", proposta);

                return new ThymeleafTemplateEngine().render(modelAndView(model, "editar_proposta"));
                
            } else {

                res.redirect("/");
            }
            
        }catch(Exception error){
            
            return error.toString();
        }

        return "";
    }

    public static String editProposta(Request req, Response res){

        try{
            Boolean edit = false;
            PropRealizadasRepository propRealizadasRepository = new PropRealizadasRepository();

            PropostaModel propostaInfo = new PropostaModel();
            propostaInfo.setPkcodproposta(Integer.parseInt(req.params(":pkcodproposta").toString()));
            propostaInfo.setTitulo(req.queryParams("titulo").toString());
            propostaInfo.setTipo(req.queryParams("tipo").toString());
            propostaInfo.setDescricao(req.queryParams("descricao").toString());

            edit = propRealizadasRepository.updateProposta(propostaInfo);

            if(edit == false){

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
