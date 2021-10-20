package br.bandtec.com.projetoimove.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Renovacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dataHoraRenovacao;
    private LocalDateTime dataHoraDevolucao;

    public Renovacao(LocalDateTime dataHoraRenovacao, LocalDateTime dataHoraDevolucao) {
        this.dataHoraRenovacao = dataHoraRenovacao;
        this.dataHoraDevolucao = dataHoraDevolucao;
    }

    public Renovacao() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraRenovacao() {
        return dataHoraRenovacao;
    }

    public void setDataHoraRenovacao(LocalDateTime dataHoraRenovacao) {
        this.dataHoraRenovacao = dataHoraRenovacao;
    }

    public LocalDateTime getDataHoraDevolucao() {
        return dataHoraDevolucao;
    }

    public void setDataHoraDevolucao(LocalDateTime dataHoraDevolucao) {
        this.dataHoraDevolucao = dataHoraDevolucao;
    }
}
