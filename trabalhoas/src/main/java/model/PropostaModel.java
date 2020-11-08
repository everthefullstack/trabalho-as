package model;

import java.sql.Timestamp;

public class PropostaModel {

    private int pkcodproposta;
    private String titulo;
    private String descricao;
    private int tipo;
    private Timestamp dataHora;
    private int fkcodusuario;
}
