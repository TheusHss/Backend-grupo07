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
    private String categoria;
    private Integer aro;
    private String cor;

    public Bicicleta(Integer id, String categoria, Integer aro, String cor) {
        this.id = id;
        this.categoria = categoria;
        this.aro = aro;
        this.cor = cor;
    }

    public Bicicleta(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getAro() {
        return aro;
    }

    public void setAro(Integer aro) {
        this.aro = aro;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
