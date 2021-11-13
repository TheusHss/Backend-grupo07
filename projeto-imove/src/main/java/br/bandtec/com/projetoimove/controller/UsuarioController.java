package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.ArquivoCSV;
import br.bandtec.com.projetoimove.ArquivoTXT;
import br.bandtec.com.projetoimove.ListaObj;
import br.bandtec.com.projetoimove.domains.Usuario;
import br.bandtec.com.projetoimove.model.Mail;
import br.bandtec.com.projetoimove.repository.UsuarioRepository;
import br.bandtec.com.projetoimove.service.MailService;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

//    ArquivoCSV funtionsCSV = new ArquivoCSV();
    ArquivoTXT gravaTxt = new ArquivoTXT();
//    ListaObj<Usuario> listaObj = new ListaObj();

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private MailService mailService;

    @GetMapping("/todos")
    public ResponseEntity obterUsuarios() {
        List<Usuario> usuarios = repository.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
//            funtionsCSV.leExibeArquivo("log-cadastro");
            return ResponseEntity.status(200).body(usuarios);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario) {
//        listaObj.adiciona(usuario);
        repository.save(usuario);
//        funtionsCSV.gravaLista(listaObj, "log-cadastro");
//        ListaObj<Usuario> listaObj = new ListaObj();
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/enviar-email-senha/{email}")
    public ResponseEntity enviarEmail(@PathVariable String email) {
        String nome = null;
        try {
            List<Usuario> usuarios = repository.findAll();

            if (!usuarios.isEmpty()) {
                for (Usuario u : usuarios) {
                    if (u.getEmail().equals(email)) {
                        nome = u.getNome();
                        Mail mail = new Mail();
                        mail.setMailFrom(email);
                        mail.setMailTo(email);
                        mail.setMailSubject("Código para alterar sua senha");
                        long digitos = (long) (0000 + Math.random() * 8999);
                        mail.setMailContent("Olá " + nome + ", Seu código:\n\n" + digitos +
                                "\n\nNão compartilhe!!!" +
                                "\nIMOVE AGRADEÇE");
                        mailService.sendEmail(mail);
                        return ResponseEntity.status(200).body(digitos);
                    }
                }
            }
            return ResponseEntity.status(404).build();
        } catch (Exception err) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/alterar-senha/{email}/{senha}")
    public ResponseEntity alterarSenha(@PathVariable String email, @PathVariable String senha) {
        List<Usuario> usuarios = repository.findAll();
        if (!usuarios.isEmpty()) {
            for (Usuario u : usuarios) {
                if (u.getEmail().equals(email)) {
                    u.setSenha(senha);
                    repository.save(u);
                    return ResponseEntity.status(200).build();
                }
            }
        }
        return ResponseEntity.status(404).build();
    }


    @PostMapping("/autenticar")
    public ResponseEntity autenticarUsuario(@RequestBody Usuario usuario) {
        for (Usuario usuarioItem : repository.findAll()) {
            if (usuarioItem.getEmail().equals(usuario.getEmail()) && usuarioItem.obterSenha().equals(usuario.obterSenha())) {
                usuarioItem.setAutenticado(true);
                usuarioItem.setUltimaAutenticado(LocalDateTime.now());
                usuarioItem.setDataLogin(LocalDateTime.now());
                repository.save(usuarioItem);
                return ResponseEntity.status(200).body(usuarioItem);
            }
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/sair")
    public ResponseEntity sairLocatario(@RequestBody Usuario usuario) {
        for (Usuario u : repository.findAll()) {
            if (u.getEmail().equals(usuario.getEmail())) {
                if (u.getAutenticado()) {
                    u.setDataLogin(null);
                    u.setAutenticado(false);
                    u.setDataLogin(null);
                    repository.save(u);
                    return ResponseEntity.status(200).build();
                }
            }
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/excluir")
    public ResponseEntity apagarUsuario(@RequestBody Usuario usuario) {
        List<Usuario> usuarios = repository.findAll();
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(u.getEmail()) && u.obterSenha().equals(u.obterSenha())) {
                usuario.setAutenticado(true);
                repository.deleteById(u.getId());
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.status(400).build();
    }
}