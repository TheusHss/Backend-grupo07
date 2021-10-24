package br.bandtec.com.projetoimove.domains;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tb_renovacao")
@Entity
public class Renovacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_renovacao", nullable = false)
    private Integer id;
    @Column(name = "data_hora_renovacao")
    private LocalDateTime dataHoraRenovacao;
    @Column(name = "data_hora_devolucao")
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
