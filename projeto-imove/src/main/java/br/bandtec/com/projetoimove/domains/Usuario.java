package br.bandtec.com.projetoimove.domains;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // define o campo como valor autoincremento
    private Integer id;

    private String nome;
    public String sobrenome;
    private String email;
    public String senha;
    public String cpf;
    public String telefone;
    private String tipoUsuario;
    private Boolean autenticado;
    private Date dataLogin;
    private Date ultimaAutenticado;
//    private Date dataCadastro;

    public Usuario(String nome, String email, String senha, String tipoUsuario, String sobrenome, String cpf, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.autenticado = false;
        this.dataLogin = null;
        this.ultimaAutenticado = null;
//        this.dataCadastro = null;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(){

    }

    public String getUltimaAutenticado() {
        if (ultimaAutenticado != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return formatter.format(ultimaAutenticado);
        } else {
            return null;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUltimaAutenticado(Date ultimaAutenticado) {
        this.ultimaAutenticado = ultimaAutenticado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getDataLogin() {
        if (dataLogin != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return formatter.format(dataLogin);
        } else {
            return null;
        }
    }

    public void setDataLogin(Date dataLogin) {
        this.dataLogin = dataLogin;
    }

//    public String getDataCadastro() {
//
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        return formatter.format(dataCadastro);
//    }
//
//    public void setDataCadastro(Date dataCadastro) {
//        this.dataCadastro = dataCadastro;
//    }

}
