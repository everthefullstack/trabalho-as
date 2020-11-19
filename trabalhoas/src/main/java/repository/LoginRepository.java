package repository;

import java.io.IOException;
import java.sql.*;
import model.UsuarioModel;
import service.DatabaseConnection;

public class LoginRepository {

    public int doLogin(UsuarioModel loginInfo) throws SQLException, IOException {

        int pkcodusuario = 0;

          DatabaseConnection db = new DatabaseConnection();
          ResultSet result = db.Executor().executeQuery("SELECT" +
                                                          " PKCODUSUARIO" +
                                                        " FROM" + 
                                                          " TBUSUARIO" +
                                                        " WHERE" + 
                                                          " LOGIN = '" + loginInfo.getLogin() + "'" +
                                                        " AND"+
                                                          " SENHA = '" + loginInfo.getSenha() + "'"); 
          while(result.next()){
            pkcodusuario = result.getInt("pkcodusuario");
              if(pkcodusuario > 0){
                db.Executor().executeQuery("UPDATE TBUSUARIO SET ID ='" + loginInfo.getId() + "' WHERE pkcodusuario = " + pkcodusuario);
              }
          }

      return pkcodusuario;
    }
}
