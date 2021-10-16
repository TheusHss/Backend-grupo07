package br.bandtec.com.projetoimove.domains;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    public String sobrenome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    public String senha;

    @Column(name = "cpf")
    public String cpf;

    @Column(name = "telefone")
    public String telefone;

    @Column(name = "tipo_usuario")
    private String tipoUsuario;

    @Column(name = "autenticado")
    private Boolean autenticado;

    @Column(name = "data_login")
    private LocalDateTime dataLogin;

    @Column(name = "ultima_autenticacao")
    private LocalDateTime ultimaAutenticado;

    public Usuario(String nome, String sobrenome, String email, String senha, String cpf, String telefone, String tipoUsuario, Boolean autenticado, LocalDateTime dataLogin, LocalDateTime ultimaAutenticado) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
        this.autenticado = autenticado;
        this.dataLogin = dataLogin;
        this.ultimaAutenticado = ultimaAutenticado;
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

    public String getSenha() {
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
