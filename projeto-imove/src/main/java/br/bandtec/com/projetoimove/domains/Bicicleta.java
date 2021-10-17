package br.bandtec.com.projetoimove.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String marca;
    private String modelo;
    private String categoria;
    private String tamanhoAro;
    private String cor;
    private String velocidade;
    private String valor_hora;

    public Bicicleta(Integer id, String marca, String modelo, String categoria,
                     String tamanhoAro, String cor, String velocidade, String valor_hora) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.categoria = categoria;
        this.tamanhoAro = tamanhoAro;
        this.cor = cor;
        this.velocidade = velocidade;
        this.valor_hora = valor_hora;
    }

    public Bicicleta(){

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

    public String getValor_hora() {
        return valor_hora;
    }

    public void setValor_hora(String valor_hora) {
        this.valor_hora = valor_hora;
    }
}
