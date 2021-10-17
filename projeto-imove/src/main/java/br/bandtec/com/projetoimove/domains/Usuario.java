package br.bandtec.com.projetoimove.domains;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    public String sobrenome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private String tipoUsuario;
    private Boolean autenticado;
    private LocalDateTime dataLogin;
    private LocalDateTime ultimaAutenticado;

    public Usuario(String nome, String sobrenome, String email, String senha, String cpf, String telefone, String tipoUsuario) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String obterSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public LocalDateTime getDataLogin() {
        return dataLogin;
    }

    public void setDataLogin(LocalDateTime dataLogin) {
        this.dataLogin = dataLogin;
    }

    public LocalDateTime getUltimaAutenticado() {
        return ultimaAutenticado;
    }

    public void setUltimaAutenticado(LocalDateTime ultimaAutenticado) {
        this.ultimaAutenticado = ultimaAutenticado;
    }
}
