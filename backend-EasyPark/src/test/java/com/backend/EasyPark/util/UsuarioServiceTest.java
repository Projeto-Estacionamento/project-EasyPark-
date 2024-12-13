package com.backend.EasyPark.util;


import com.backend.EasyPark.model.dto.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class UsuarioServiceTest {

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("Nathan");
        usuarioDTO.setEmail("nathan154@gmail.com.br");
        usuarioDTO.setCpf("12345678900");
        usuarioDTO.setTelefone("123456789");
    }

    @Test
    void validaCriacaoDeUsuario() {
        assertNotNull(usuarioDTO, "O UsuarioDTO não deve ser nulo");
        assertEquals("Nathan", usuarioDTO.getNome(), "O nome do usuário deve ser 'Nathan'");
        assertEquals("nathan154@gmail.com.br", usuarioDTO.getEmail(), "O email deve ser 'nathan154@gmail.com.br'");
        assertEquals("12345678900", usuarioDTO.getCpf(), "O CPF deve ser '12345678900'");
    }

    @Test
    void validaEmailUsuario() {
        String emailInvalido = "nathan154gmail.com.br";

        assertTrue(usuarioDTO.getEmail().contains("@"), "O email deve conter '@'");
        assertFalse(emailInvalido.contains("@"), "Email inválido não deve conter '@'");
    }
}