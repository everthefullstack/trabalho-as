package repository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import model.PropostaModel;
import service.DatabaseConnection;

public class ProcurarRepository {

    public ArrayList selectPropostas(String procurar, String id) throws SQLException, IOException {
        
        ArrayList<ArrayList> lista = new ArrayList();

        DatabaseConnection db = new DatabaseConnection();
        ResultSet result = db.Executor().executeQuery("SELECT" +
                                                            " pkcodproposta," +
                                                            " titulo," +
                                                            " descricao," +
                                                            " case tipo when 0 then 'Serviço' else 'Produto' end as tipo," +
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
                                                        " AND titulo LIKE '%" + procurar + "%'" +
                                                        " AND id != '" + id + "'");
        while(result.next()){

            ArrayList<String> res = new ArrayList();
            res.add(result.getString("pkcodproposta"));
            res.add(result.getString("titulo"));
            res.add(result.getString("descricao"));
            res.add(result.getString("tipo"));
            res.add(result.getString("nome"));
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
                                                        " AND id != '" + id + "'");
        while(result.next()){

            res.add(result.getString("pkcodproposta"));
            res.add(result.getString("titulo"));
            res.add(result.getString("descricao"));
            res.add(result.getString("tipo"));
        }

        return res;
    }

    public boolean insertProposta(PropostaModel anuncioInfo, String fkcodproposta1) throws SQLException, IOException {

        DatabaseConnection db = new DatabaseConnection();
        ResultSet result = db.Executor().executeQuery("INSERT INTO" +
                                                            " tbproposta" +
                                                            " (pkcodproposta, titulo, descricao, tipo, ativo, oferta, datahora, fkcodusuario)" + 
                                                        " VALUES" +
                                                            "(TBPROPOSTA_PK.nextval," +
                                                            "'" + anuncioInfo.getTitulo() + "'," +
                                                            "'" + anuncioInfo.getDescricao() + "'," +
                                                            "'" + anuncioInfo.getTipo() + "'," + //0 serviço | 1 produto
                                                            "1," + //0 nao ativo | 1 ativo
                                                            "1," + //0 oferta não | 1 oferta sim
                                                            "sysdate," +
                                                            "'" + anuncioInfo.getFkcodusuario() +"')");
        
        result = db.Executor().executeQuery("SELECT" +
                                                " MAX(pkcodproposta)as pkcodproposta" +
                                            " FROM" +
                                                " tbproposta" +
                                            " WHERE" +
                                                " oferta = 1" +
                                            " AND" + 
                                                " fkcodusuario = " + anuncioInfo.getFkcodusuario());
        result.next();
        int fkcodproposta2 = result.getInt("pkcodproposta");

        result = db.Executor().executeQuery("INSERT INTO" +
                                                " tbnegociacao" +
                                                " (pkcodnegociacao, data_hora, ativo, aceita, fkcodtbproposta1, fkcodtbproposta2)" + 
                                            " VALUES" +
                                                "(TBNEGOCIACAO_PK.nextval," +
                                                "sysdate," +
                                                "1," + //0 nao ativo | 1 ativo
                                                "2," + //0 não | 1 sim | 2 aguardando | 3 proposta editada
                                                fkcodproposta1 + "," +
                                                fkcodproposta2 + ")");

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