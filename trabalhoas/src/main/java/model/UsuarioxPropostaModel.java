package model;

public class UsuarioxPropostaModel extends UsuarioModel{

    private int pkcodpropostaUm;
    private String tituloUm;
    private String descricao;
    private String tipo;
    private String dataHora;
    private String ativo;
    private int pkcodpropostaDois;
    private String tituloDois;

    public int getPkcodpropostaUm() {
        return pkcodpropostaUm;
    }

    public void setPkcodpropostaUm(int pkcodpropostaUm) {
        this.pkcodpropostaUm = pkcodpropostaUm;
    }

    public String getTituloUm() {
        return tituloUm;
    }

    public void setTituloUm(String tituloUm) {
        this.tituloUm = tituloUm;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public int getPkcodpropostaDois() {
        return pkcodpropostaDois;
    }

    public void setPkcodpropostaDois(int pkcodpropostaDois) {
        this.pkcodpropostaDois = pkcodpropostaDois;
    }

    public String getTituloDois() {
        return tituloDois;
    }

    public void setTituloDois(String tituloDois) {
        this.tituloDois = tituloDois;
    }
}
