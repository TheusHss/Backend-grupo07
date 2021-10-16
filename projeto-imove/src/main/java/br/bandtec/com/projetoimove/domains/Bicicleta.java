package br.bandtec.com.projetoimove.domains;

import javax.persistence.*;

@Entity
@Table(name = "tb_bicicleta")
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bibicleta")
    private Integer id;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "tamanho_aro")
    private String tamanhoAro;

    @Column(name = "cor")
    private String cor;

    @Column(name = "velocidade")
    private String velocidade;

    @Column(name = "valor_hora")
    private String valor_hora;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    public Bicicleta(String marca, String modelo, String categoria, String tamanhoAro, String cor, String velocidade, String valor_hora) {
        this.marca = marca;
        this.modelo = modelo;
        this.categoria = categoria;
        this.tamanhoAro = tamanhoAro;
        this.cor = cor;
        this.velocidade = velocidade;
        this.valor_hora = valor_hora;
    }

    public Bicicleta() {

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
