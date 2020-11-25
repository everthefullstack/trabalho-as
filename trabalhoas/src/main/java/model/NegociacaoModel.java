package model;

import java.sql.Timestamp;

public class NegociacaoModel {

    private int pkcodnegociacao;
    private int ativo;
    private String aceita;
    private Timestamp dataHora;
    private int fkcodtbproposta1;
    private int fkcodtbproposta2;

    public int getPkcodnegociacao() {
        return pkcodnegociacao;
    }

    public void setPkcodnegociacao(int pkcodnegociacao) {
        this.pkcodnegociacao = pkcodnegociacao;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public String getAceita() {
        return aceita;
    }

    public void setAceita(String aceita) {
        this.aceita = aceita;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public int getFkcodtbproposta1() {
        return fkcodtbproposta1;
    }

    public void setFkcodtbproposta1(int fkcodtbproposta1) {
        this.fkcodtbproposta1 = fkcodtbproposta1;
    }

    public int getFkcodtbproposta2() {
        return fkcodtbproposta2;
    }

    public void setFkcodtbproposta2(int fkcodtbproposta2) {
        this.fkcodtbproposta2 = fkcodtbproposta2;
    }
}
