package routes;

import static spark.Spark.*;
import controller.LoginController;
import controller.SobreController;
import controller.CadastroController;
import controller.IndexController;

public class Router {
    
    public void getAllRoutes(){

        //Rotas da página de login
        get("/", LoginController::getLoginPage);
        post("/", LoginController::loginPage);

        //Rotas de logout
        get("/logout", LoginController::logoutPage);

        //Rotas da página de index
        get("/index", IndexController::getIndexPage);

        //Rotas da página de cadastro
        get("/cadastro", CadastroController::getCadastroPage);
        post("/cadastro", CadastroController::createCadastro);

        //Rotas da página de sobre
        get("/sobre", SobreController::getSobrePage);
    }
}