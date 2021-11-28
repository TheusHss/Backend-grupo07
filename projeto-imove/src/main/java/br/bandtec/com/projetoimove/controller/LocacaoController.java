package br.bandtec.com.projetoimove.controller;


import br.bandtec.com.projetoimove.domains.Bicicleta;
import br.bandtec.com.projetoimove.domains.Locacao;
import br.bandtec.com.projetoimove.repository.BicicletaRepository;
import br.bandtec.com.projetoimove.repository.LocacaoRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locacao")
public class LocacaoController {

    @Autowired
    private LocacaoRepository repository;

    @Autowired
    private BicicletaRepository bicicletaRepository;

    @PostMapping("/cadastrar-locacao")
    public ResponseEntity cadastrarLocacao(@RequestBody Locacao locacao) {
        repository.save(locacao);
        Bicicleta b = bicicletaRepository.getById(locacao.getBicicleta().getId());
        b.setAlocada(true);
        bicicletaRepository.save(b);
        return ResponseEntity.status(201).body(locacao.getId());
    }

    @GetMapping("/consultar-locacao/{id}")
    public ResponseEntity consultarLocacao(@PathVariable int id){
        Optional<Locacao> locacao =  repository.findById(id);
        return ResponseEntity.status(200).body(locacao);
    }

    @GetMapping("/consultar-locacao-uso/{id}")
    public ResponseEntity consultarLocacaoUso(@PathVariable int id){
        List<Locacao> locacao = repository.findAll();
        List<Locacao> lista = new ArrayList<>();

        for (Locacao l : locacao){
            if (l.getUsuarioLocatario().getId().equals(id)){
                lista.add(l);
            }
        }
        if(lista.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(locacao);
    }

    @GetMapping("/consultar-locacao-locador/{id}")
    public ResponseEntity consultarLocador(@PathVariable int id){
        List<Locacao> locacao = repository.findAll();

        for (Locacao l : locacao) {
            if (l.getBicicleta().getUsuario().getId().equals(id)) {
                return ResponseEntity.status(200).body(l);
            }
        }
            return ResponseEntity.status(404).build();
    }

    @DeleteMapping("cancelar/{id}")
    public ResponseEntity removerLocacao(@PathVariable int id) {
        if (repository.existsById(id)) {
            Locacao locacao = repository.getById(id);
            repository.deleteById(id);
            Bicicleta b = bicicletaRepository.getById(locacao.getBicicleta().getId());
            b.setAlocada(false);
            bicicletaRepository.save(b);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
