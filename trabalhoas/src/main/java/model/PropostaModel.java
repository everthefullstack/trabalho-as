package model;

import java.sql.Timestamp;

public class PropostaModel {

    private int pkcodproposta;
    private String titulo;
    private String descricao;
    private String tipo;
    private String foto1;
    private String foto2;
    private String foto3;
    private Timestamp dataHora;
    private int fkcodusuario;

    public int getPkcodproposta() {
        return pkcodproposta;
    }

    public void setPkcodproposta(int pkcodproposta) {
        this.pkcodproposta = pkcodproposta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String string) {
        this.tipo = string;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public int getFkcodusuario() {
        return fkcodusuario;
    }

    public void setFkcodusuario(int fkcodusuario) {
        this.fkcodusuario = fkcodusuario;
    }
}
