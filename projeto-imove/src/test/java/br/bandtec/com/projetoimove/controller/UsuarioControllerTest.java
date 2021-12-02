package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.domains.Endereco;
import br.bandtec.com.projetoimove.domains.Usuario;
import br.bandtec.com.projetoimove.model.Mail;
import br.bandtec.com.projetoimove.repository.EnderecoRepository;
import br.bandtec.com.projetoimove.repository.UsuarioRepository;
import br.bandtec.com.projetoimove.service.MailService;
import br.bandtec.com.projetoimove.service.MailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UsuarioController.class, UsuarioRepository.class})
class UsuarioControllerTest {

    @Autowired
    UsuarioController controller;

    @MockBean
    MailService mailService;

    @MockBean
    UsuarioRepository repository;

    @MockBean
    EnderecoRepository enderecoRepository;


    @Test
    void getUsuariosTodos() {
        when(repository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity resposta = controller.obterUsuarios();

        assertEquals(204, resposta.getStatusCode().value());
        assertNull(resposta.getBody());
    }

    @Test
    void getUsuarioPorId() {
        Usuario usuario = new Usuario();

        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(usuario));

        ResponseEntity resposta = controller.usuarioPorId(1);

        assertEquals(404, resposta.getStatusCode().value());
        assertNull(resposta.getBody());
    }

    @Test
    void cadastrarUsuario() {
        Usuario usuario = new Usuario();

        when(repository.save(usuario)).thenReturn(usuario);

        ResponseEntity resposta = controller.cadastrarUsuario(usuario);

        assertEquals(201, resposta.getStatusCode().value());
    }

    @Test
    void mudarSenha() {
        Usuario usuario = new Usuario();

        when(repository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity resposta = controller.alterarSenha("teste101@gmail.com", "admin");

        assertEquals(repository.save(usuario), resposta.getBody());
        assertEquals(404, resposta.getStatusCode().value());
    }

    @Test
    void alterarCadastro() {
        Usuario usuario = new Usuario();

        when(repository.findAll()).thenReturn((new ArrayList<>()));
        ResponseEntity resposta = controller.alterarUsuario(usuario);

        assertEquals(404, resposta.getStatusCode().value());
    }

    @Test
    void verificaCodigo() {
        ResponseEntity resposta = controller.validarCodigo("2345");

        assertEquals(404, resposta.getStatusCode().value());
        assertNull(resposta.getBody());
    }

    @Test
    void alterarSenha() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity resposta = controller.alterarSenha("teste@gmail.com", "1234", "123456");

        assertEquals(404, resposta.getStatusCode().value());
    }

    @Test
    void getImagem() {
        Integer idImg = 10;

        when(repository.existsById(idImg)).thenReturn(false);

        ResponseEntity resposta = controller.getFoto(idImg);

        assertEquals(404, resposta.getStatusCode().value());
        assertNull(resposta.getBody());

    }
}