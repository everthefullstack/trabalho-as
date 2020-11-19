package routes;

import static spark.Spark.*;
import controller.LoginController;
import controller.MeuPerfilController;
import controller.ProcurarController;
import controller.PropRealizadasController;
import controller.PropRecebidasController;
import controller.SobreController;
import controller.AnunciarController;
import controller.CadastroController;
import controller.IndexController;

public class Router {
    
    public void getAllRoutes(){

        //Rotas da página de login
        get("/", LoginController::getLoginPage);
        post("/", LoginController::loginPage);

        //Rotas da página de index
        get("/index", IndexController::getIndexPage);

        //Rotas da página de cadastro
        get("/cadastro", CadastroController::getCadastroPage);
        post("/cadastro", CadastroController::createCadastro);

        //Rotas da página de meu perfil
        get("/meu_perfil", MeuPerfilController::getMeuPerfilPage);
        post("/meu_perfil", MeuPerfilController::editPerfil);

        //Rotas da página de meu propostas procurar
        get("/procurar", ProcurarController::getProcurarPage);

        //Rotas da página de meu anunciar
        get("/anunciar", AnunciarController::getAnunciarPage);
        post("/anunciar", AnunciarController::createAnunciar);

        //Rotas da página de meu propostas realizadas
        get("/propostas_realizadas", PropRealizadasController::getPropRealizadasPage);

        //Rotas da página de meu propostas recebidas
        get("/propostas_recebidas", PropRecebidasController::getPropRecebidasPage);

        //Rotas da página de sobre
        get("/sobre", SobreController::getSobrePage);

        //Rotas de logout
        get("/logout", LoginController::logoutPage);
    }
}