package br.bandtec.com.projetoimove.repository;

import br.bandtec.com.projetoimove.domains.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {
}
