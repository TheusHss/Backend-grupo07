package br.bandtec.com.projetoimove.controller;


import br.bandtec.com.projetoimove.domains.Imagem;
import br.bandtec.com.projetoimove.repository.ImagemRepository;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/imagem")
public class ImagemController {

    @Autowired
    private ImagemRepository repository;

    @PostMapping("/{id}")
    public ResponseEntity<FileInfo> uploadImagem(@RequestParam("file") MultipartFile inputFile, @PathVariable int id) throws IOException {
        byte [] byteArr= inputFile.getBytes();
        Imagem imagem = new Imagem(1,byteArr);
        repository.save(imagem);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getFoto(@PathVariable int id) {
        if (repository.existsById(id)) {
             Imagem imagem = repository.findById(id).get();
            return ResponseEntity
                    .status(200)
                    .header("content-type", "image/jpeg")
                    .body(imagem.getImagem());
        }
        return ResponseEntity.status(404).build();
    }
}
