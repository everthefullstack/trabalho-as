package repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.PropostaModel;
import service.DatabaseConnection;

public class MeusAnunciosRepository {

    public ArrayList selectPropostas(String id) throws SQLException, IOException {
        
        ArrayList<ArrayList> lista = new ArrayList();

        DatabaseConnection db = new DatabaseConnection();
        ResultSet result = db.Executor().executeQuery("SELECT" +
                                                            " pkcodproposta," +
                                                            " titulo," +
                                                            " descricao," +
                                                            " case tipo when 0 then 'Serviço' else 'Produto' end as tipo," +
                                                            " nome," +
                                                            " case ativo when 0 then 'Inativo' else 'Ativo' end as ativo" +
                                                        " FROM" + 
                                                            " TBPROPOSTA pr" +
                                                        " INNER JOIN" +
                                                            " TBUSUARIO us on "+
                                                            "us.PKCODUSUARIO=pr.FKCODUSUARIO" +
                                                        " WHERE" +
                                                            " ativo = 1" +
                                                        " AND" +
                                                            " oferta = 0" +
                                                        " AND id = '" + id + "'");
        while(result.next()){

            ArrayList<String> res = new ArrayList();
            res.add(result.getString("pkcodproposta"));
            res.add(result.getString("titulo"));
            res.add(result.getString("descricao"));
            res.add(result.getString("tipo"));
            res.add(result.getString("nome"));
            res.add(result.getString("ativo"));
            lista.add(res);
        }

        return lista;
    }

    public ArrayList selectProposta(String pkcodproposta, String id) throws SQLException, IOException {
        
        ArrayList<String> res = new ArrayList();

        DatabaseConnection db = new DatabaseConnection();
        ResultSet result = db.Executor().executeQuery("SELECT" +
                                                            " pkcodproposta," +
                                                            " titulo," +
                                                            " descricao," +
                                                            " case tipo when 0 then 'Serviço' else 'Produto' end as tipo" +
                                                        " FROM" + 
                                                            " TBPROPOSTA pr" +
                                                        " INNER JOIN" +
                                                            " TBUSUARIO us on "+
                                                            "us.PKCODUSUARIO=pr.FKCODUSUARIO" +
                                                        " WHERE" +
                                                            " pkcodproposta = " + pkcodproposta +
                                                        " AND id = '" + id + "'");
        while(result.next()){

            res.add(result.getString("pkcodproposta"));
            res.add(result.getString("titulo"));
            res.add(result.getString("descricao"));
            res.add(result.getString("tipo"));
        }

        return res;
    }

    public boolean updateProposta(PropostaModel propostaInfo) throws SQLException, IOException {

        DatabaseConnection db = new DatabaseConnection();
        db.Executor().executeQuery("UPDATE tbproposta" +
                                    " SET" + 
                                      " titulo = '" + propostaInfo.getTitulo() + "'," +
                                      " tipo = '" + propostaInfo.getTipo() + "'," +
                                      " descricao = '" + propostaInfo.getDescricao() + "'" +
                                    " WHERE" +
                                      " pkcodproposta = " + propostaInfo.getPkcodproposta());

        db.Executor().executeQuery("UPDATE tbnegociacao" +
                                      " SET" + 
                                        " aceita = 3" +
                                      " WHERE" +
                                        " fkcodtbproposta1 = " + propostaInfo.getPkcodproposta());
        return true;
    }
}