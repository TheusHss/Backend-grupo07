package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.domains.Bicicleta;
import br.bandtec.com.projetoimove.domains.Endereco;
import br.bandtec.com.projetoimove.repository.EnderecoRepository;
import br.bandtec.com.projetoimove.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarEndereco(@RequestBody Endereco endereco) {
        repository.save(endereco);
        return ResponseEntity.status(201).body(endereco.getId());
    }

    @DeleteMapping("remover/{id}")
    public ResponseEntity removerEndereco(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
