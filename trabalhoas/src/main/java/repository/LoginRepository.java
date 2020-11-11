package repository;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

import service.DatabaseConnection;

public class LoginRepository {

    public int doLogin(HashMap loginInfo) throws SQLException, IOException {

        int fkcodusuario = 0;

          DatabaseConnection db = new DatabaseConnection();
          ResultSet result = db.Executor().executeQuery("SELECT" +
                                                          " FKCODUSUARIO" +
                                                        " FROM" + 
                                                          " TBLOGIN" +
                                                        " WHERE" + 
                                                          " LOGIN = '" + loginInfo.get("login") + "'" +
                                                        " AND"+
                                                          " SENHA = '" + loginInfo.get("senha") + "'"); 
          while(result.next()){
              fkcodusuario = result.getInt("fkcodusuario");
              if(fkcodusuario > 0){
                db.Executor().executeQuery("UPDATE TBLOGIN SET ID ='" + loginInfo.get("id") + "' WHERE fkcodusuario = " + fkcodusuario);
              }
          }

      return fkcodusuario;
    }
}
