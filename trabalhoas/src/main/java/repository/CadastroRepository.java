package repository;

import java.io.IOException;
import java.sql.*;
import model.UsuarioModel;
import service.DatabaseConnection;

public class CadastroRepository {

    public boolean insertCadastro(UsuarioModel cadastroInfo) throws SQLException, IOException {

        DatabaseConnection db = new DatabaseConnection();
        db.Executor().executeQuery("INSERT INTO" +
                                      " tbusuario" +
                                      " (pkcodusuario, nome, cpf, telefone, login, senha)" + 
                                  " VALUES" +
                                      "(TBUSUARIO_PK.nextval," +
                                      "'" + cadastroInfo.getNome() + "'," +
                                      "'" + cadastroInfo.getCpf() + "'," +
                                      "'" + cadastroInfo.getTelefone() + "'," +
                                      "'" + cadastroInfo.getLogin() + "'," +
                                      "'" + cadastroInfo.getSenha() + "')");
        return true;
    }
}