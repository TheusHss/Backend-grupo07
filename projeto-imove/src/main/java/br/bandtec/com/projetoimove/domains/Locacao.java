package br.bandtec.com.projetoimove.domains;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tb_locacao")
@Entity
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locacao", nullable = false)
    private Integer id;
    @Column(name = "forma_pagamento")
    private String formaPagamento;
    @Column(name = "data_hora_locacao")
    private LocalDateTime dataHoraLocacao;
    @Column(name = "data_hora_devolucao")
    private LocalDateTime dataHoraDevolucao;

    public Locacao(String formaPagamento, LocalDateTime dataHoraLocacao, LocalDateTime dataHoraDevolucao) {
        this.formaPagamento = formaPagamento;
        this.dataHoraLocacao = dataHoraLocacao;
        this.dataHoraDevolucao = dataHoraDevolucao;
    }

    public Locacao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDateTime getDataHoraLocacao() {
        return dataHoraLocacao;
    }

    public void setDataHoraLocacao(LocalDateTime dataHoraLocacao) {
        this.dataHoraLocacao = dataHoraLocacao;
    }

    public LocalDateTime getDataHoraDevolucao() {
        return dataHoraDevolucao;
    }

    public void setDataHoraDevolucao(LocalDateTime dataHoraDevolucao) {
        this.dataHoraDevolucao = dataHoraDevolucao;
    }
}
