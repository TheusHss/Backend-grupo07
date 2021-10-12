package br.bandtec.com.projetoimove.repository;

import br.bandtec.com.projetoimove.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


}
