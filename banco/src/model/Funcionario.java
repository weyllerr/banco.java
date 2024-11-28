package model;

import java.io.Serializable;

public class Funcionario implements Serializable {
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String usuario;
    private String senha;

    // Construtor completo
    public Funcionario(String nome, String cpf, String telefone, String endereco, String usuario, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.usuario = usuario;
        this.senha = senha;
    }

    // Construtor simplificado para login
    public Funcionario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
        this.nome = ""; // Valores padrão para evitar problemas
        this.cpf = "";
        this.telefone = "";
        this.endereco = "";
    }

    // Getters e Setters
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

   @Override
   public String toString() {
       return "Funcionario{" +
               "nome='" + nome + '\'' +
               ", cpf='" + cpf + '\'' +
               ", telefone='" + telefone + '\'' +
               ", endereco='" + endereco + '\'' +
               ", usuario='" + usuario + '\'' +
               '}';
   }
}