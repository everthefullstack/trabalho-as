package controller;

import static spark.Spark.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import repository.LoginRepository;

public class ControllerLogin {

    public static String getLoginPage(Request req, Response res) {

        Map<String, Object> model = new HashMap<>();
        return new ThymeleafTemplateEngine().render(modelAndView(model, "login"));
    }

    public static String loginPage(Request req, Response res) throws SQLException, IOException {

        LoginRepository lr = new LoginRepository();
        HashMap<String, String> loginInfo = new HashMap<>();
        loginInfo.put("login", req.queryParams("login"));
        loginInfo.put("senha", req.queryParams("senha"));

        int result = lr.validateLogin(loginInfo);

        if(result == 0){
            res.redirect("/");

        } else {
            res.redirect("/index");
        }

        return null;
    }
}
