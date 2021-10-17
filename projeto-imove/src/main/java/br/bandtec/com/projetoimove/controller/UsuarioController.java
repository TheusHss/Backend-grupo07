package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.ListaObj;
import br.bandtec.com.projetoimove.domains.Usuario;
import br.bandtec.com.projetoimove.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    public static void gravaLista(ListaObj<Usuario> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        boolean deuRuim = false;
        nomeArq += ".csv";
        try {
            arq = new FileWriter(nomeArq, true);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.err.println("Erro ao abrir arquivo");
            System.exit(1);
        }
        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                Usuario user = lista.getElemento(i);
                saida.format("%d;%s;%s;%s;%s;%s;%s\n", user.getId(),
                        user.getNome(), user.getSobrenome(), user.getEmail(),
                        user.getCpf(), user.getTelefone(), user.getTipoUsuario());
            }
        } catch (FormatterClosedException erro) {
            System.err.println("Erro ao gravar no arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leExibeArquivo(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.err.println("Arquivo não encontrado");
            System.exit(1);
        }

        try {
        System.out.printf("%4s %-15s %-20s %-35s %-15s %-20s %-10s\n", "ID", "NOME", "SOBRENOME", "EMAIL", "CPF", "TELEFONE", "TIPO DE USUARIO");
            while (entrada.hasNext()) {
                int id = entrada.nextInt();
                String nome = entrada.next();
                String sobrenome = entrada.next();
                String email = entrada.next();
                String cpf = entrada.next();
                String telefone = entrada.next();
                String tipoUser = entrada.next();
                System.out.printf("%4d %-15s %-20s %-35s %-15s %-20s %-10s\n", id, nome, sobrenome, email, cpf, telefone, tipoUser);
            }
        } catch (NoSuchElementException erro) {
            System.err.println("Arquivo com problemas.");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.err.println("Erro na leitura do arquivo.");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    ListaObj<Usuario> listaObj = new ListaObj(10000);

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/todos")
    public ResponseEntity obterUsuarios() {
        List<Usuario> usuarios = repository.findAll();
        leExibeArquivo("log-cadastro");
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(usuarios);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario) {
        listaObj.adiciona(usuario);
        repository.save(usuario);
        gravaLista(listaObj, "log-cadastro");
        listaObj.limpa();
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticarUsuario(@RequestBody Usuario usuario) {
        for (Usuario usuarioItem : repository.findAll()) {
            if (usuarioItem.getEmail().equals(usuario.getEmail()) && usuarioItem.obterSenha().equals(usuario.obterSenha())) {
                usuarioItem.setAutenticado(true);
                repository.save(usuarioItem);
                return ResponseEntity.status(201).build();
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
                return ResponseEntity.status(201).build();
            }
        }
        return ResponseEntity.status(400).build();
    }

}
