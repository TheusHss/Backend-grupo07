package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.ArquivoTXT;
import br.bandtec.com.projetoimove.FilaObj;
import br.bandtec.com.projetoimove.PilhaObj;
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
public class BicicletaController {


    ArquivoTXT gravaTxt = new ArquivoTXT();
    PilhaObj<Bicicleta> pilha = new PilhaObj(500);
    PilhaObj<Bicicleta> pilhaAux = new PilhaObj(500);

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

    @GetMapping("/bicicleta/{id}")
    public ResponseEntity bicicletaPorId(@PathVariable int id) {
        if (repository.existsById(id)) {
            return ResponseEntity.status(200).body(repository.findById(id));
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/bicicleta-por-usuario/{id}")
    public ResponseEntity bicicletaId(@PathVariable int id) {
        List<Bicicleta> bike = repository.findAll();
        List<Bicicleta> bicicletasPorUsuario = new ArrayList<>();
        if (!bike.isEmpty()) {
            for (Bicicleta bicicleta : bike) {
                if (bicicleta.getUsuario() != null && bicicleta.getUsuario().getId().equals(id)) {
                    bicicletasPorUsuario.add(bicicleta);
                }
            }
            return ResponseEntity.status(200).body(bicicletasPorUsuario);
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarBicicleta(@RequestBody Bicicleta bike) {
        repository.save(bike);
        pilha.push(bike);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/remover-ultima")
    public ResponseEntity removerBike() {
        if (!pilha.isEmpty()) {
            Bicicleta aux = pilha.pop();
            Bicicleta original = repository.getById(aux.getId());
            byte[] imagem = original.getImagem();
            aux.setImagem(imagem);
            pilhaAux.push(aux);
            repository.delete(aux);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(204).build();

    }

    @PostMapping("/adicionar-ultima")
    public ResponseEntity adicionarBike() {
        if (!pilhaAux.isEmpty()) {
            pilha.push(pilhaAux.peek());
            repository.save(pilhaAux.pop());
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();

    }

    @DeleteMapping("remover/{id}")
    public ResponseEntity removerBicicleta(@PathVariable int id) {
        if (repository.existsById(id)) {
            Bicicleta b = repository.getById(id);
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/filtrar/{categoria}/{aro}/{cor}/{velocidade}")
    public ResponseEntity filtroBicicletas(@PathVariable String categoria, @PathVariable String aro, @PathVariable String cor, @PathVariable String velocidade) {
        List<Bicicleta> bicicletasFiltradas = new ArrayList<>();



        boolean verificador = false;
        boolean verificador1 = false;
        boolean verificador2 = false;
        boolean verificador3 = false;

        for (Bicicleta bicicleta : repository.findAll()){
            if (!bicicleta.getCategoria().equals(categoria)){
                verificador = true;
            }
            if(verificador && categoria.equals("null")){
                verificador = false;
            }
            if (!bicicleta.getTamanhoAro().equals(aro)){
                verificador1 = true;
            }
            if(verificador1 &&aro.equals("null")){
                verificador1 = false;
            }
            if (!bicicleta.getCor().equals(cor)){
                verificador2 = true;
            }
            if(verificador2 &&cor.equals("null")){
                verificador2 = false;
            }
            if (!bicicleta.getVelocidade().equals(velocidade)){
                verificador3 = true;
            }
            if(verificador3 &&velocidade.equals("null")){
                verificador3 = false;
            }
            if (!verificador && !verificador1 && !verificador2 && !verificador3){
                bicicletasFiltradas.add(bicicleta);
            }
           verificador = false;
            verificador1 = false;
            verificador2 = false;
            verificador3 = false;
        }

        if (bicicletasFiltradas.size() > 0) {
            return ResponseEntity.status(200).body(bicicletasFiltradas);
        } else {
            return ResponseEntity.status(404).body(bicicletasFiltradas);
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

    @PostMapping("/envio-arquivo/file/{id}")
    public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile inputFile, @PathVariable int id) {
        FileInfo fileInfo = new FileInfo();
        HttpHeaders headers = new HttpHeaders();
        if (!inputFile.isEmpty()) {
            try {
                File file = new File("Arquivo-bike-retornado.txt");
                var path = Paths.get(file.getAbsolutePath());
                inputFile.transferTo(path);
                headers.add("Arquivo recebido - ", "bicicleta");

                gravaTxt.leArquivoRetonadoEgravaTxt("Arquivo-bike-retornado.txt", repository, pilha, id);

                return new ResponseEntity<FileInfo>(fileInfo, headers, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<FileInfo>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/bicicleta-imagem/{id}")
    public ResponseEntity<FileInfo> uploadImagem(@RequestParam("file") MultipartFile inputFile, @PathVariable int id) throws IOException {
        byte [] byteArr= inputFile.getBytes();
        List<Bicicleta> bike = repository.findAll();
        if (!bike.isEmpty()){
            for (Bicicleta b : repository.findAll()){
                if (b.getId().equals(id)){
                    b.setImagem(byteArr);
                    repository.save(b);
                    return ResponseEntity.ok().build();
                }
            }

        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/bicicleta-imagem/{id}")
    public ResponseEntity getFoto(@PathVariable int id) {
        if (repository.existsById(id)) {
            Bicicleta b = repository.getById(id);
            if (b.getImagem() != null){
                return ResponseEntity
                        .status(200)
                        .header("content-type", "image/jpeg")
                        .body(b.getImagem());
            }
        }
        return ResponseEntity.status(404).build();
    }


    @GetMapping("/exportar-arquivo-total-bicicletas")
    public ResponseEntity<?> total() {
        List<Bicicleta> bike = repository.findAll();
        gravaTxt.gravaArquivoTxt(bike,"total-bikes.txt");

        var filename = String.format("total-bikes.txt");

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

}
