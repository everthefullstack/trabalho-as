package repository;

import java.io.IOException;
import java.sql.*;
import model.PropostaModel;
import service.DatabaseConnection;

public class AnunciarRepository {
    
    public boolean insertAnuncio(PropostaModel anuncioInfo) throws SQLException, IOException {

        DatabaseConnection db = new DatabaseConnection();
        db.Executor().executeQuery("INSERT INTO" +
                                      " tbproposta" +
                                      " (pkcodproposta, titulo, descricao, tipo, datahora, fkcodusuario)" + 
                                  " VALUES" +
                                      "(TBPROPOSTA_PK.nextval," +
                                      "'" + anuncioInfo.getTitulo() + "'," +
                                      "'" + anuncioInfo.getDescricao() + "'," +
                                      "'" + anuncioInfo.getTipo() + "'," +
                                      "sysdate," +
                                      "'" + anuncioInfo.getFkcodusuario() +"')");
        return true;
    }

    public int selectFkCodUsuario(String id) throws SQLException, IOException {

        int res = 0;

        DatabaseConnection db = new DatabaseConnection();
        ResultSet result = db.Executor().executeQuery("SELECT" +
                                                        " pkcodusuario" +
                                                      " FROM" +
                                                        " TBUSUARIO" +
                                                      " WHERE" +
                                                        " ID = '" + id + "'");
        while(result.next()){

            res = result.getInt("pkcodusuario");
        }

        return res;
    }
}