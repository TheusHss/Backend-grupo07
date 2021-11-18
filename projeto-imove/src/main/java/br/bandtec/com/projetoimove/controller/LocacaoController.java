package br.bandtec.com.projetoimove.controller;


import br.bandtec.com.projetoimove.domains.Locacao;
import br.bandtec.com.projetoimove.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/locacao")
public class LocacaoController {

    @Autowired
    private LocacaoRepository repository;

    @PostMapping("/cadastrar-locacao")
    public ResponseEntity cadastrarLocacao(@RequestBody Locacao locacao) {
        repository.save(locacao);
        return ResponseEntity.status(201).body(locacao.getId());
    }

    @GetMapping("/consultar-locacao/{id}")
    public ResponseEntity consultarLocacao(@PathVariable int id){
        Optional<Locacao> locacao =  repository.findById(id);
        return ResponseEntity.status(200).body(locacao);
    }

    @DeleteMapping("cancelar/{id}")
    public ResponseEntity removerLocacao(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
