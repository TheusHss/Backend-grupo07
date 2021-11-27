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
    @Column(name = "imagem", length = 50_000_000)
    private byte[] imagem;
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
    @Column(name = "alocada")
    private Boolean alocada;
    @ManyToOne
    private Usuario usuario;


    public Bicicleta(Integer id, byte[] imagem, String marca, String modelo, String categoria, String tamanhoAro, String cor, String velocidade, Usuario usuario, Boolean alocada) {
        this.id = id;
        this.imagem = imagem;
        this.marca = marca;
        this.modelo = modelo;
        this.categoria = categoria;
        this.tamanhoAro = tamanhoAro;
        this.cor = cor;
        this.velocidade = velocidade;
        this.usuario = usuario;
        this.alocada = alocada;
    }

    public Bicicleta(){

    }

    public Boolean getAlocada() {
        return alocada;
    }

    public void setAlocada(Boolean alocada) {
        this.alocada = alocada;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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
}
