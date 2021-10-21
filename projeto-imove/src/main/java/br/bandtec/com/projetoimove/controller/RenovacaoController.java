package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.domains.Bicicleta;
import br.bandtec.com.projetoimove.domains.Locacao;
import br.bandtec.com.projetoimove.domains.Renovacao;
import br.bandtec.com.projetoimove.repository.BicicletaRepository;
import br.bandtec.com.projetoimove.repository.LocacaoRepository;
import br.bandtec.com.projetoimove.repository.RenovacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/renovacao")
public class RenovacaoController {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private RenovacaoRepository renovacaoRepository;

    @PostMapping("/cadastrar-renovacao/{id}")
    public ResponseEntity cadastrarRenovacao(@PathVariable int id, @RequestBody Renovacao renovacao) {
        if (locacaoRepository.existsById(id)){
            renovacaoRepository.save(renovacao);
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
