package br.bandtec.com.projetoimove.controller;

import br.bandtec.com.projetoimove.domains.Usuario;
import br.bandtec.com.projetoimove.repository.UsuarioRepository;
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
    UsuarioRepository repository;

    @Test
    void getUsuarios() {
        Usuario usuario = new Usuario();

        when(repository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity resposta = controller.obterUsuarios();

        assertEquals(usuario, resposta.getBody());
        assertEquals(204, resposta.getStatusCode().equals(null));
        assertEquals(200, resposta.getStatusCode().equals(usuario));
    }

}