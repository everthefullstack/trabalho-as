package controller;

import static spark.Spark.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import model.UsuarioModel;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import repository.LoginRepository;
import service.IsLogged;

public class LoginController {

    public static String getLoginPage(Request req, Response res) {

        try{
            IsLogged isLogged = new IsLogged();
            if(isLogged.isLogged(req.cookie("usuario")) == true){

                res.redirect("/index");

            } else {

                Map<String, Object> model = new HashMap<>();
                return new ThymeleafTemplateEngine().render(modelAndView(model, "login"));
            }
            
        }catch(Exception error){

            return error.toString();
        } 

        return "";
    }

    public static String loginPage(Request req, Response res) throws SQLException, IOException {

        try{
            LoginRepository loginRep = new LoginRepository();
            UsuarioModel loginInfo = new UsuarioModel();
            final String uuid = UUID.randomUUID().toString().replace("-", "");
            loginInfo.setLogin(req.queryParams("login").toString());
            loginInfo.setSenha(req.queryParams("senha").toString());
            loginInfo.setId(uuid.toString());

            int fkcodusuario = loginRep.doLogin(loginInfo);

            if(fkcodusuario == 0){
                res.redirect("/");

            } else {
                res.removeCookie("usuario");
                res.cookie("usuario", uuid);
                res.redirect("/index");
            }

        } catch(Exception error){

            return error.toString();
        }

        return "";
    }

    public static String logoutPage(Request req, Response res) throws SQLException, IOException {

        try{
            res.removeCookie("usuario");
            res.redirect("/");

        } catch(Exception error) {

            return error.toString();
        }

        return "";
    }
}