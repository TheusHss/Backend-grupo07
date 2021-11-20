package br.bandtec.com.projetoimove.domains;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Table(name = "tb_bicicleta")
@Entity
public class Bicicleta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bicicleta", nullable = false)
    private Integer id;
    @NotBlank
    @Column(name = "marca")
    private String marca;
    @NotBlank
    @Column(name = "modelo")
    private String modelo;
    @NotBlank
    @Column(name = "categoria")
    private String categoria;
    @NotBlank
    @Column(name = "tamanho_aro")
    private String tamanhoAro;
    @NotBlank
    @Column(name = "cor")
    private String cor;
    @NotBlank
    @Column(name = "velocidade")
    private String velocidade;
    @NotBlank
    @Column(name = "valor_hora")
    private String valorHora;
    @NotBlank
    @Column(name = "imagem")
    private String imagem;
    @ManyToOne
    private Usuario usuario;


    public Bicicleta(Integer id, String marca, String modelo, String categoria, String tamanhoAro, String cor, String velocidade, String valorHora, Usuario usuario) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.categoria = categoria;
        this.tamanhoAro = tamanhoAro;
        this.cor = cor;
        this.velocidade = velocidade;
        this.valorHora = valorHora;
        this.usuario = usuario;
    }

    public Bicicleta(){

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTamanhoAro() {
        return tamanhoAro;
    }

    public void setTamanhoAro(String tamanhoAro) {
        this.tamanhoAro = tamanhoAro;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(String velocidade) {
        this.velocidade = velocidade;
    }

    public String getValorHora() {
        return valorHora;
    }

    public void setValorHora(String valorHora) {
        this.valorHora = valorHora;
    }
}
