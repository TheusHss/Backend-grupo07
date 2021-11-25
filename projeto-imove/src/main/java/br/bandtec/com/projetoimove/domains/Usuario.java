package br.bandtec.com.projetoimove.domains;

import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;
import javax.persistence.*;

@Table(name = "tb_usuario")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;
    @Column(name = "imagem", length = 50_000_000)
    private byte[] imagem;
    @NotBlank
    @Column(name = "nome")
    private String nome;
    @NotBlank
    @Column(name = "sobrenome")
    public String sobrenome;
    @NotBlank
    @Column(name = "email")
    private String email;
    @NotBlank
    @Column(name = "senha")
    private String senha;
    @NotBlank
    @Column(name = "cpf", length = 11)
    private String cpf;
    @ManyToOne
    private Endereco endereco;
    @NotBlank
    @Column(name = "telefone")
    private String telefone;
    @NotBlank
    @Column(name = "tipo_usuario")
    private String tipoUsuario;
    @Column(name = "autenticado")
    private Boolean autenticado;
    @Column(name = "data_login")
    private LocalDateTime dataLogin;
    @Column(name = "ultima_autenticacao")
    private LocalDateTime ultimaAutenticado;



    public Usuario(String nome, byte[] imagem, String sobrenome, String email, String senha, String cpf, String telefone, String tipoUsuario, Boolean autenticado) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
        this.autenticado= autenticado;
    }

    public Usuario(){
    }


    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
