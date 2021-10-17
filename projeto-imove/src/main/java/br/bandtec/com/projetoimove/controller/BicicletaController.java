package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.domains.Bicicleta;
import br.bandtec.com.projetoimove.domains.Usuario;
import br.bandtec.com.projetoimove.repository.BicicletaRepository;
import br.bandtec.com.projetoimove.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("pesquisar/{categoria}")
    public ResponseEntity filtarBicicletas(@PathVariable String categoria){
        List<Bicicleta> bicicletas = repository.findAll();

        List<Bicicleta> bicicletasFiltradas = new ArrayList<>();
        for (Bicicleta bicicleta: bicicletas){
            if (bicicleta.getCategoria().equals(categoria)){
                bicicletasFiltradas.add(bicicleta);
            }
        }

        if (bicicletasFiltradas.size() > 0){
            return ResponseEntity.status(200).body(bicicletasFiltradas);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("pesquisar/{categoria}/{tamanhoAro}")
    public ResponseEntity filtarBicicletas(@PathVariable String categoria, @PathVariable String tamanhoAro){
        List<Bicicleta> bicicletas = repository.findAll();

        List<Bicicleta> bicicletasFiltradas = new ArrayList<>();
        for (Bicicleta bicicleta: bicicletas){
            if (bicicleta.getCategoria().equals(categoria) && bicicleta.getTamanhoAro().equals(tamanhoAro)){
                bicicletasFiltradas.add(bicicleta);
            }
        }

        if (bicicletasFiltradas.size() > 0){
            return ResponseEntity.status(200).body(bicicletasFiltradas);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("pesquisar/{categoria}/{tamanhoAro}/{cor}")
    public ResponseEntity filtarBicicletas(@PathVariable String categoria, @PathVariable String tamanhoAro, @PathVariable String cor){
        List<Bicicleta> bicicletas = repository.findAll();

        List<Bicicleta> bicicletasFiltradas = new ArrayList<>();
        for (Bicicleta bicicleta: bicicletas){
            if (bicicleta.getCategoria().equals(categoria)
                    && bicicleta.getTamanhoAro().equals(tamanhoAro)
                    && bicicleta.getCor().equals(cor)){
                bicicletasFiltradas.add(bicicleta);
            }
        }

        if (bicicletasFiltradas.size() > 0){
            return ResponseEntity.status(200).body(bicicletasFiltradas);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("pesquisar/{categoria}/{tamanhoAro}/{cor}/{velocidade}")
    public ResponseEntity filtarBicicletas(@PathVariable String categoria, @PathVariable String tamanhoAro, @PathVariable String cor, @PathVariable String velocidade){
        List<Bicicleta> bicicletas = repository.findAll();

        List<Bicicleta> bicicletasFiltradas = new ArrayList<>();
        for (Bicicleta bicicleta: bicicletas){
            if (bicicleta.getCategoria().equals(categoria)
                    && bicicleta.getTamanhoAro().equals(tamanhoAro)
                    && bicicleta.getCor().equals(cor)
                    && bicicleta.getVelocidade().equals(velocidade)){
                bicicletasFiltradas.add(bicicleta);
            }
        }

        if (bicicletasFiltradas.size() > 0){
            return ResponseEntity.status(200).body(bicicletasFiltradas);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("pesquisar/{categoria}/{tamanhoAro}/{cor}/{velocidade}/{preco}")
    public ResponseEntity filtarBicicletas(@PathVariable String categoria, @PathVariable String tamanhoAro, @PathVariable String cor, @PathVariable String velocidade, @PathVariable String preco){
        List<Bicicleta> bicicletas = repository.findAll();

        List<Bicicleta> bicicletasFiltradas = new ArrayList<>();
        for (Bicicleta bicicleta: bicicletas){
            if (bicicleta.getCategoria().equals(categoria)
                    && bicicleta.getTamanhoAro().equals(tamanhoAro)
                    && bicicleta.getCor().equals(cor)
                    && bicicleta.getVelocidade().equals(velocidade)
                    && bicicleta.getValorHora().equals(preco)){
                bicicletasFiltradas.add(bicicleta);
            }
        }

        if (bicicletasFiltradas.size() > 0){
            return ResponseEntity.status(200).body(bicicletasFiltradas);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
