package br.bandtec.com.projetoimove.controller;


import br.bandtec.com.projetoimove.domains.Locacao;
import br.bandtec.com.projetoimove.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locacao")
public class LocacaoController {

    @Autowired
    private LocacaoRepository repository;

    @PostMapping("/cadastrar-locacao")
    public ResponseEntity cadastrarLocacao(@RequestBody Locacao locacao) {
        repository.save(locacao);
        return ResponseEntity.status(201).build();
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
