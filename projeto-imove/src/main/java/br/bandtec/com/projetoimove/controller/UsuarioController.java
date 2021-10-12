package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.domains.Usuario;
import br.bandtec.com.projetoimove.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/todos")
    public ResponseEntity obterUsuarios() {
        List<Usuario> usuarios = repository.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(usuarios);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario) {
        repository.save(usuario);
        return ResponseEntity.status(201).build();
    }

//    @PostMapping("/autenticar/{id}")
//    public ResponseEntity autenticarLocatario(@RequestBody Usuario usuario, @PathVariable int id) {
//        if (repository.existsById(id)) {
//            usuario.setAutenticado(true);
//            repository.save(usuario);
//            return ResponseEntity.status(200).build();
//        } else {
//            return ResponseEntity.status(404).build();
//        }
//    }

//    @PostMapping("/sair")
//    public ResponseEntity sairLocatario(@RequestBody Usuario usuario) {
//        List<Usuario> usuarios = repository.findAll();
//        for (Usuario u : usuarios) {
//            if (u.getEmail().equals(usuario.getEmail())) {
//                if (u.getAutenticado()) {
//                    u.setDataLogin(null);
//                    u.setAutenticado(false);
//                    return ResponseEntity.status(200).build();
//                }
//                return ResponseEntity.status(400).build();
//            }
//        }
//        return ResponseEntity.status(404).build();
//    }

    @DeleteMapping("/excluir")
    public ResponseEntity apagarUsuario(@RequestBody Usuario usuario) {
        List<Usuario> usuarios = repository.findAll();
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(usuario.getEmail()) && u.senha.equals(usuario.senha)) {
                usuarios.remove(u);
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.status(400).build();
    }

//    @GetMapping("/pesquisa/{nome}")
//    public ResponseEntity procurarUsuario(@PathVariable String nome) {
//        for (Usuario usuario : usuarios) {
//            if (usuario.getNome().equals(nome) || usuario.getEmail().equals(nome) || usuario.getTipoUsuario() .equals(nome)) {
//                return ResponseEntity.status(204).build();
//            }
//        }
//        return ResponseEntity.status(404).body(nome);
//    }
}
