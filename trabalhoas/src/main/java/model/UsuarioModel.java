package model;

public class UsuarioModel {

    private int pkcodusuario;
    private String nome;
    private String cpf;
    private String telefone;
    private String foto;

    public int getPkcodusuario() {
        return pkcodusuario;
    }

    public void setPkcodusuario(int pkcodusuario) {
        this.pkcodusuario = pkcodusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public UsuarioModel(int pkcodusuario, String nome, String cpf, String telefone, String foto) {
        this.pkcodusuario = pkcodusuario;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.foto = foto;
    }
}
