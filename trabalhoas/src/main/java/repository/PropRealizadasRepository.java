package repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.PropostaModel;
import service.DatabaseConnection;

public class PropRealizadasRepository {
    
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
                                                            " oferta = 1" +
                                                        " AND id = '" + id + "'");

        ResultSet result2 = db.Executor().executeQuery("select" + 
                                                            " pr.pkcodproposta as proposta2," +
                                                            " pr.titulo as titulo2" +                                   
                                                        " from" +
                                                            " tbproposta pr" +
                                                        " inner join" +
                                                            " tbusuario us on pr.fkcodusuario=us.pkcodusuario" +
                                                        " where" +
                                                            " pr.pkcodproposta in" +
                                                                "(select" +
                                                                    " fkcodtbproposta1" +
                                                                " from" + 
                                                                    " tbnegociacao" +
                                                                " where" +
                                                                    " fkcodtbproposta2 in(" +
                                                                        "select" + 
                                                                            " pr.pkcodproposta" +
                                                                        " from" +
                                                                            " tbusuario us" +
                                                                        " inner join" +
                                                                            " tbproposta pr on pr.fkcodusuario=us.pkcodusuario" +
                                                                        " where"+
                                                                            " us.id = '" + id + "'))");  
        while(result.next()){

            ArrayList<String> res = new ArrayList();
            res.add(result.getString("pkcodproposta"));
            res.add(result.getString("titulo"));
            res.add(result.getString("descricao"));
            res.add(result.getString("tipo"));
            res.add(result.getString("nome"));
            res.add(result.getString("ativo"));

            result2.next();
            res.add(result2.getString("proposta2"));
            res.add(result2.getString("titulo2"));
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
                                                            " oferta = 1" +
                                                        " AND"+
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
                                        " fkcodtbproposta2 = " + propostaInfo.getPkcodproposta());
        return true;
    }
}
