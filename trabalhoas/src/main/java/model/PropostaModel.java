package model;

import java.sql.Timestamp;

public class PropostaModel {

    private int pkcodproposta;
    private String titulo;
    private String descricao;
    private String tipo;
    private int ativo;
    private int oferta;
    private String foto1;
    private String foto2;
    private String foto3;
    private Timestamp dataHora;
    private int fkcodusuario;

    public int getOferta() {
        return oferta;
    }

    public void setOferta(int oferta) {
        this.oferta = oferta;
    }
    
    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
    
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
