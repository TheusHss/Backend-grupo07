package br.bandtec.com.projetoimove.domains;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Table(name = "tb_imagem")
@Entity
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagem", nullable = false)
    private Integer id;
    @Column(name = "imagem", length = 50_000_000)
    private byte[] imagem;

    public Imagem(Integer id, byte[] imagem) {
        this.id = id;
        this.imagem = imagem;
    }

    public Imagem() {}

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
