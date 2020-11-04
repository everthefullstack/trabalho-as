package model;

import java.sql.Timestamp;

public class ModelTbServico {
    
    private int pkcodservico;
    private String nomeServico;
    private String descricao;
    private Timestamp dataHora;
    private int fkcodusuario;
}
