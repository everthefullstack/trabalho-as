package model;

public class UsuarioModel {

    private int pkcodusuario;
    private String nome;
    private String cpf;
    private String telefone;
    private String login;
    private String senha;
    private String id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }    
}
