package br.bandtec.com.projetoimove.repository;

import br.bandtec.com.projetoimove.domains.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
}
