package br.bandtec.com.projetoimove.controller;


import br.bandtec.com.projetoimove.domains.Endereco;
import br.bandtec.com.projetoimove.domains.LocacaoBicicleta;
import br.bandtec.com.projetoimove.repository.LocacaoBicicletaRepository;
import br.bandtec.com.projetoimove.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locacao")
public class LocacaoBicicletaController {

    @Autowired
    private LocacaoBicicletaRepository repository;

    @PostMapping("/cadastrar-locacao")
    public ResponseEntity cadastrarLocacao(@RequestBody LocacaoBicicleta locacao) {
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
