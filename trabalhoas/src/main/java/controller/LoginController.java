package controller;

import static spark.Spark.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import repository.LoginRepository;

public class LoginController {

    public static String getLoginPage(Request req, Response res) {

        try{
            Map<String, Object> model = new HashMap<>();
            return new ThymeleafTemplateEngine().render(modelAndView(model, "login"));

        }catch(Exception error){

            return error.toString();
        } 
    }

    public static String loginPage(Request req, Response res) throws SQLException, IOException {

        try{
            LoginRepository loginRep = new LoginRepository();
            HashMap<String, String> loginInfo = new HashMap<>();
            final String uuid = UUID.randomUUID().toString().replace("-", "");
            loginInfo.put("login", req.queryParams("login"));
            loginInfo.put("senha", req.queryParams("senha"));
            loginInfo.put("id", uuid);

            int fkcodusuario = loginRep.doLogin(loginInfo);

            if(fkcodusuario == 0){
                res.redirect("/");

            } else {
                res.removeCookie("usuario");
                res.cookie("usuario", uuid);
                res.redirect("/index");
            }

            return "";

        } catch(Exception error){

            return error.toString();
        }
    }

    public static String logoutPage(Request req, Response res) throws SQLException, IOException {

        try{
            res.removeCookie("usuario");
            res.redirect("/");

            return "";

        } catch(Exception error) {

            return error.toString();
        }
    }
}