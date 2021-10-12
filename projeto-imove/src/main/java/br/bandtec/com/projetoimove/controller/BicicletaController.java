package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.domains.Bicicleta;
import br.bandtec.com.projetoimove.domains.Usuario;
import br.bandtec.com.projetoimove.repository.BicicletaRepository;
import br.bandtec.com.projetoimove.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bicicleta")
public class BicicletaController {

    @Autowired
    private BicicletaRepository repository;
    
    @GetMapping("/todos")
    public ResponseEntity getUsuarios() {
        List<Bicicleta> bike = repository.findAll();
        if (bike.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(bike);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarBicicleta(@RequestBody Bicicleta bike) {
        repository.save(bike);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("remover/{id}")
    public ResponseEntity removerBicicleta(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
