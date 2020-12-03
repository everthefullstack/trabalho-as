package trabalhoas;

import routes.Router;
import service.ThreadModerador;

public class App{
    public static void main(String[] args) {

        new Router().getAllRoutes();
        ThreadModerador tm = new ThreadModerador();
        Thread t1 = new Thread(tm);
        t1.start();
        
        //http://localhost:4567
    }
}