package trabalhoas;

import static spark.Spark.*;
import routes.Router;

public class App {

    public static void main(String[] args) {
        
        staticFileLocation("/static");
        new Router().getAllRoutes();
                    
        //http://localhost:4567
    }
}