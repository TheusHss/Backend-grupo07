package br.bandtec.com.projetoimove.domains;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(name = "tb_endereco")
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco", nullable = false)
    private Integer id;
    @NotBlank
    @Column(name = "cep")
    private String cep;
    @NotBlank
    @Column(name = "estado")
    private String estado;
    @NotBlank
    @Column(name = "cidade")
    private String cidade;
    @NotBlank
    @Column(name = "bairro")
    private String bairro;
    @NotBlank
    @Column(name = "rua")
    private String rua;
    @NotBlank
    @Column(name = "numero")
    private String numero;

    public Endereco(Integer id, String cep, String estado, String cidade, String bairro, String rua, String numero) {
        this.id = id;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }
    public Endereco(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
