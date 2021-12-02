package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.ArquivoCSV;
import br.bandtec.com.projetoimove.ArquivoTXT;
import br.bandtec.com.projetoimove.ListaObj;
import br.bandtec.com.projetoimove.domains.Bicicleta;
import br.bandtec.com.projetoimove.domains.Endereco;
import br.bandtec.com.projetoimove.domains.Usuario;
import br.bandtec.com.projetoimove.model.Mail;
import br.bandtec.com.projetoimove.repository.EnderecoRepository;
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
    int codigoArmazenado;
//    ListaObj<Usuario> listaObj = new ListaObj();

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

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

    @GetMapping("/{id}")
    public ResponseEntity usuarioPorId(@PathVariable int id) {
        if (repository.existsById(id)) {
            return ResponseEntity.status(200).body(repository.findById(id));
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario) {
//        listaObj.adiciona(usuario);
        repository.save(usuario);
//        funtionsCSV.gravaLista(listaObj, "log-cadastro");
//        ListaObj<Usuario> listaObj = new ListaObj();
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/alterar-cadastro")
    public ResponseEntity alterarUsuario(@RequestBody Usuario usuario) {
        Endereco end = usuario.getEndereco();
        List<Usuario> usuarios = repository.findAll();
        for(Usuario ur: usuarios){
            if (ur.getId().equals(usuario.getId())){
                ur.setNome(usuario.getNome());
                ur.setSobrenome(usuario.getSobrenome());
                ur.setCpf(usuario.getCpf());
                ur.setTelefone(usuario.getTelefone());
                repository.save(ur);
                enderecoRepository.save(end);
                return ResponseEntity.status(201).build();
            }
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/verificar-codigo/{codigo}")
    public ResponseEntity validarCodigo(@PathVariable String codigo) {
        int codigoRecebido = Integer.parseInt(codigo);
        if (codigoRecebido == codigoArmazenado) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
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
                        int digitos = (int) (0000 + Math.random() * 8999);
                        mail.setMailContent("Olá " + nome + ", Seu código:\n\n" + digitos +
                                "\n\nNão compartilhe!!!" +
                                "\nIMOVE AGRADECE");
                        mailService.sendEmail(mail);
                        codigoArmazenado = digitos;
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


    @PutMapping("/alterar-senha/{email}/{senha}/{novaSenha}")
    public ResponseEntity alterarSenha(@PathVariable String email, @PathVariable String senha, @PathVariable String novaSenha) {
        List<Usuario> usuarios = repository.findAll();
        if (!usuarios.isEmpty()) {
            for (Usuario u : usuarios) {
                if (u.getEmail().equals(email) && u.obterSenha().equals(senha)) {
                    u.setSenha(novaSenha);
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


    @PostMapping("/imagem/{id}")
    public ResponseEntity<FileInfo> uploadImagem(@RequestParam("file") MultipartFile inputFile, @PathVariable int id) throws IOException {
        byte [] byteArr= inputFile.getBytes();
        List<Usuario> usuarios = repository.findAll();
        if (!usuarios.isEmpty()){
            for (Usuario b : usuarios){
                if (b.getId().equals(id)){
                    b.setImagem(byteArr);
                    repository.save(b);
                    return ResponseEntity.ok().build();
                }
            }

        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/imagem/{id}")
    public ResponseEntity getFoto(@PathVariable int id) {
        if (repository.existsById(id)) {
            Usuario b = repository.getById(id);
            if (b.getImagem() != null){
                return ResponseEntity
                        .status(200)
                        .header("content-type", "image/jpeg")
                        .body(b.getImagem());
            }
        }
        return ResponseEntity.status(404).build();
    }

}