package routes;

import static spark.Spark.*;
import controller.ControllerLogin;
import controller.ControllerIndex;

public class Router {
    
    public void getAllRoutes(){

        //Rotas da página de login
        get("/", ControllerLogin::getLoginPage);
        post("/", ControllerLogin::loginPage);

        //Rotas da página de index
        get("/index", ControllerIndex::getIndexPage);
    }
}
