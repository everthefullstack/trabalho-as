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

    public void verifyPalavra() throws SQLException, IOException {

        DatabaseConnection db = new DatabaseConnection();

        ResultSet result = db.Executor().executeQuery("SELECT PALAVRA FROM TBPALAVRARESTRITA");

        while(result.next()){

          db.Executor().executeQuery("UPDATE tbproposta" +
                                      " SET" + 
                                          " ativo = 0" +
                                      " WHERE" +
                                          " ativo = 1" +
                                      " AND" +
                                          " titulo" +
                                      " LIKE" +
                                        "'%" + result.getString("PALAVRA") + "%'");
        }
    }
}