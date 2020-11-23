package model;

public class UsuarioxPropostaModel extends UsuarioModel{

    private int pkcodproposta;
    private String titulo;
    private String descricao;
    private String tipo;
    private String dataHora;

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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }   
}
