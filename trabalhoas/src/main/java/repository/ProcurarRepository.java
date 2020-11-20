package repository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import service.DatabaseConnection;

public class ProcurarRepository {

    public ArrayList selectPropostas(String procurar) throws SQLException, IOException {
        
        ArrayList<ArrayList> lista = new ArrayList();

        DatabaseConnection db = new DatabaseConnection();
        ResultSet result = db.Executor().executeQuery("SELECT" +
                                                            " pkcodproposta," +
                                                            " titulo," +
                                                            " descricao," +
                                                            " case tipo when 0 then 'Serviço' else 'Produto' end as tipo," +
                                                            " dataHora," +
                                                            " nome" +
                                                        " FROM" + 
                                                            " TBPROPOSTA pr" +
                                                        " INNER JOIN" +
                                                            " TBUSUARIO us on "+
                                                            "us.PKCODUSUARIO=pr.FKCODUSUARIO" +
                                                        " WHERE" +
                                                            " ativo = 1" +
                                                        " AND" +
                                                            " oferta = 0" +
                                                        " AND titulo LIKE '%" + procurar + "%'");
        while(result.next()){

            ArrayList<String> res = new ArrayList();
            res.add(result.getString("pkcodproposta"));
            res.add(result.getString("titulo"));
            res.add(result.getString("descricao"));
            res.add(result.getString("tipo"));
            res.add(result.getString("dataHora"));
            res.add(result.getString("nome"));
            lista.add(res);
        }

        return lista;
    }
}