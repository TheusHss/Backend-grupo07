package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.ArquivoTXT;
import br.bandtec.com.projetoimove.domains.Bicicleta;
import br.bandtec.com.projetoimove.domains.Usuario;
import br.bandtec.com.projetoimove.repository.BicicletaRepository;
import br.bandtec.com.projetoimove.repository.UsuarioRepository;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bicicleta")
public class BicicletaController{


    ArquivoTXT gravaTxt = new ArquivoTXT();


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
    public ResponseEntity filtrarBicicletas(@PathVariable String categoria){
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
    public ResponseEntity filtrarBicicletas(@PathVariable String categoria, @PathVariable String tamanhoAro){
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
    public ResponseEntity filtrarBicicletas(@PathVariable String categoria, @PathVariable String tamanhoAro, @PathVariable String cor){
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
    public ResponseEntity filtrarBicicletas(@PathVariable String categoria, @PathVariable String tamanhoAro, @PathVariable String cor, @PathVariable String velocidade){
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
    public ResponseEntity filtrarBicicletas(@PathVariable String categoria, @PathVariable String tamanhoAro, @PathVariable String cor, @PathVariable String velocidade, @PathVariable String preco){
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

    @GetMapping("/obter-arquivo/{qtdBikes}")
    public ResponseEntity<?> download(@PathVariable int qtdBikes) {
        gravaTxt.criarArquivoTxt("Arquivo-bike.txt", qtdBikes);

        var filename = String.format("Arquivo-bike.txt");

        try {
            File file = new File(filename);
            var path = Paths.get(file.getAbsolutePath());
            var resource = new ByteArrayResource(Files.readAllBytes(path));
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(file.length())
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    ServletContext context;

    @PostMapping("/envio-arquivo")
    public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile inputFile) {
        FileInfo fileInfo = new FileInfo();
        HttpHeaders headers = new HttpHeaders();
        if (!inputFile.isEmpty()) {
            try {
                File file = new File("Arquivo-bike-retornado.txt");
                var path = Paths.get(file.getAbsolutePath());
                inputFile.transferTo(path);
                headers.add("Arquivo recebido - ", "bicicleta");

                gravaTxt.leArquivoRetonadoEgravaTxt("Arquivo-bike-retornado.txt", repository);

                return new ResponseEntity<FileInfo>(fileInfo, headers, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<FileInfo>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
        }

    }

}
