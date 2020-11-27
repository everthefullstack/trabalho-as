package repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import service.DatabaseConnection;

public class PropRecebidasRepository {
    
    public ArrayList selectPropostas(String id) throws SQLException, IOException {
        
        ArrayList<ArrayList> lista = new ArrayList();

        DatabaseConnection db = new DatabaseConnection();
        ResultSet result = db.Executor().executeQuery("select"+  
                                                        " pr.pkcodproposta," + 
                                                        " pr.titulo," +
                                                        " pr.descricao," +
                                                        " case tipo when 0 then 'Serviço' else 'Produto' end as tipo," +
                                                        " us.nome," +
                                                        " case ativo when 0 then 'Proposta aceita' else 'Ativo' end as ativo," +
                                                        " us.telefone," +
                                                        " ne.fkcodtbproposta1 as proposta2," +
                                                        " (select titulo from tbproposta where pkcodproposta = ne.fkcodtbproposta1) as titulo2" +
                                                      " from" +
                                                        " tbproposta pr" +
                                                      " inner join" +
                                                        " tbusuario us on pr.fkcodusuario=us.pkcodusuario" +
                                                      " inner join" +
                                                        " (select " +
                                                                " fkcodtbproposta2," +
                                                                " fkcodtbproposta1" +
                                                            " from" +
                                                                " tbnegociacao" +
                                                           " where" +
                                                               " fkcodtbproposta1 in(" +
                                                                    "select" +
                                                                        " pr.pkcodproposta" +
                                                                    " from" +
                                                                        " tbusuario us" +
                                                                    " inner join" +
                                                                        " tbproposta pr on pr.fkcodusuario=us.pkcodusuario" +
                                                                    " where" +
                                                                       " us.id = '" + id + "')) ne" +
                                                        " on pr.pkcodproposta=ne.fkcodtbproposta2" +
                                                     " where" +
                                                        " pr.pkcodproposta in" +
                                                            "(select" +
                                                                " fkcodtbproposta2" +
                                                            " from" +
                                                                " tbnegociacao" +
                                                            " where" +
                                                                " fkcodtbproposta1 in( " +
                                                                    "select" +
                                                                       " pr.pkcodproposta" +
                                                                    " from" +
                                                                        " tbusuario us" +
                                                                    " inner join" +
                                                                       " tbproposta pr on pr.fkcodusuario=us.pkcodusuario" +
                                                                    " where" +
                                                                       " us.id = '" + id + "'))");
                                             
        while(result.next()){

            ArrayList<String> res = new ArrayList();
            res.add(result.getString("pkcodproposta"));
            res.add(result.getString("titulo"));
            res.add(result.getString("descricao"));
            res.add(result.getString("tipo"));
            res.add(result.getString("nome"));
            res.add(result.getString("ativo"));
            res.add(result.getString("telefone"));
            res.add(result.getString("proposta2"));
            res.add(result.getString("titulo2"));
            lista.add(res);
        }

        return lista;
    }

    public ArrayList selectProposta(String pkcodproposta) throws SQLException, IOException {
        
        ArrayList<String> res = new ArrayList();

        DatabaseConnection db = new DatabaseConnection();
        ResultSet result = db.Executor().executeQuery("SELECT" +
                                                            " pkcodproposta," +
                                                            " nome," +
                                                            " telefone," +
                                                            " titulo," +
                                                            " case tipo when 0 then 'Serviço' else 'Produto' end as tipo," +
                                                            " descricao," +
                                                            " ativo" +
                                                        " FROM" + 
                                                            " TBPROPOSTA pr" +
                                                        " INNER JOIN" +
                                                            " TBUSUARIO us on "+
                                                            "us.PKCODUSUARIO=pr.FKCODUSUARIO" +
                                                        " WHERE" +
                                                            " oferta = 1" +
                                                        " AND"+
                                                            " pkcodproposta = " + pkcodproposta);
        while(result.next()){

            res.add(result.getString("pkcodproposta"));
            res.add(result.getString("nome"));
            res.add(result.getString("telefone"));
            res.add(result.getString("titulo"));
            res.add(result.getString("tipo"));
            res.add(result.getString("descricao"));
            res.add(result.getString("ativo"));
        }

        return res;
    }
   
    public boolean updateProposta(String pkcodproposta, int aceite) throws SQLException, IOException {

        DatabaseConnection db = new DatabaseConnection();

        if(aceite == 1){

            db.Executor().executeQuery("UPDATE tbproposta" +
                                        " SET" + 
                                            " ativo = 0" +
                                        " WHERE" +
                                            " oferta = 1" +
                                        " AND" +
                                            " pkcodproposta = " + pkcodproposta);

            db.Executor().executeQuery("UPDATE tbnegociacao" +
                                      " SET" + 
                                        " aceita = 1," +
                                        " ativo = 0" +
                                      " WHERE" +
                                        " fkcodtbproposta2 = " + pkcodproposta);

        } else if(aceite == 0){

            db.Executor().executeQuery("UPDATE tbnegociacao" +
                                      " SET" + 
                                        " aceita = 0," +
                                        " ativo = 0" +
                                      " WHERE" +
                                        " fkcodtbproposta2 = " + pkcodproposta);
        }

        return true;
    }
}
