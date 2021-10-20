package br.bandtec.com.projetoimove.repository;

import br.bandtec.com.projetoimove.domains.Bicicleta;
import br.bandtec.com.projetoimove.domains.Renovacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RenovacaoRepository extends JpaRepository<Renovacao, Integer> {
}
