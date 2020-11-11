package service;

import java.io.IOException;
import java.sql.SQLException;
import repository.ServiceRepository;

public class IsLogged {

    public Boolean isLogged(String id) throws SQLException, IOException {
    
        ServiceRepository sr = new ServiceRepository();
        return sr.verifyLogin(id);
    }
}
