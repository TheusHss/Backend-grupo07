package br.bandtec.com.projetoimove.repository;

import br.bandtec.com.projetoimove.domains.Endereco;
import br.bandtec.com.projetoimove.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
