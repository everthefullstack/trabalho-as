package repository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import model.UsuarioModel;
import service.DatabaseConnection;

public class MeuPerfilRepository {
    
    public ArrayList selectInfosPerfil(String id) throws SQLException, IOException {
        
        ArrayList<String> res = new ArrayList();

        DatabaseConnection db = new DatabaseConnection();
        ResultSet result = db.Executor().executeQuery("SELECT" + 
                                                            " nome," + 
                                                            " cpf," + 
                                                            " telefone," + 
                                                            " login" +
                                                        " FROM" + 
                                                            " TBUSUARIO" +
                                                        " WHERE" +
                                                            " id = '" + id + "'");
        while(result.next()){

            res.add(result.getString("nome"));
            res.add(result.getString("cpf"));
            res.add(result.getString("telefone"));
            res.add(result.getString("login"));
        }

        return res;
    }

    public boolean updatePerfil(UsuarioModel perfilInfo) throws SQLException, IOException {

        DatabaseConnection db = new DatabaseConnection();
        db.Executor().executeQuery("UPDATE tbusuario" +
                                    " SET" + 
                                      " nome = '" + perfilInfo.getNome() + "'," +
                                      " cpf = '" + perfilInfo.getCpf() + "'," +
                                      " telefone = '" + perfilInfo.getTelefone() + "'," +
                                      " login = '" + perfilInfo.getLogin() + "'," +
                                      " senha = '" + perfilInfo.getSenha() + "'");
        return true;
    }
}
