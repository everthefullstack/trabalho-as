package repository;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import service.DatabaseConnection;

public class CadastroRepository {

    public boolean insertCadastro(HashMap cadastroInfo) throws SQLException, IOException {

        String res = "";
        int fkcodusuario = 0;
        DatabaseConnection db = new DatabaseConnection();
        ResultSet result = db.Executor().executeQuery("INSERT INTO" +
                                                        " tbusuario" +
                                                        " (pkcodusuario, nome, cpf, telefone)" + 
                                                      " VALUES" +
                                                        "(TBUSUARIO_PK.nextval," +
                                                        "'" + cadastroInfo.get("nome") + "'," +
                                                        "'" + cadastroInfo.get("cpf") + "'," +
                                                        "'" + cadastroInfo.get("telefone") + "')");
        
        result = db.Executor().executeQuery("SELECT" +
                                                " pkcodusuario" +
                                            " FROM" +
                                                " tbusuario" +
                                            " WHERE" +
                                                " CPF = '" + cadastroInfo.get("cpf") + "'");

        while(result.next()){

            res = Integer.toString(result.getInt("pkcodusuario"));
            fkcodusuario = result.getInt("pkcodusuario");
        }

        if(res.isEmpty() == true){
            
            return false;
            
        } else{

            result = db.Executor().executeQuery("INSERT INTO" +
                                                    " tblogin" +
                                                    " (pkcodlogin, login, senha, fkcodusuario)" + 
                                                " VALUES" +
                                                    "(TBLOGIN_PK.nextval," +
                                                    "'" + cadastroInfo.get("login") + "'," +
                                                    "'" + cadastroInfo.get("senha") + "'," +
                                                    "'" + fkcodusuario + "')");
            return true;
        }
    }
}
