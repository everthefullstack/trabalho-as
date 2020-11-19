package repository;

import java.io.IOException;
import java.sql.*;
import service.DatabaseConnection;

public class ServiceRepository {

    public Boolean verifyLogin(String id) throws SQLException, IOException {
      
        String res = "";

        DatabaseConnection db = new DatabaseConnection();
        ResultSet result = db.Executor().executeQuery("SELECT" +
                                                        " ID" +
                                                      " FROM" +
                                                        " TBUSUARIO" +
                                                      " WHERE" +
                                                        " ID = '" + id + "'");
        while(result.next()){

            res = result.getString("ID");
        }

        if(res.isEmpty() == true){
          
          return false;
          
        } else{

          return true;

        }
    }
}