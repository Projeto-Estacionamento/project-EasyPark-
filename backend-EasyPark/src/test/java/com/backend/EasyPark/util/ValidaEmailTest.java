package com.backend.EasyPark.util;

import com.backend.EasyPark.model.dto.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidaEmailTest {

    private String emailValido = "nathan154@gmail.com.br";
    private String emailInvalido = "nathan154gmail.com.br";
    private String emailNulo = null;

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp(){
        // Remover a declaração da variável local e inicializar a variável de instância
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("nathan154@gmail.com.br");
    }

    @Test
    void CaracterArroba() {
        assertTrue(ValidaEmail.validaCaracTerArroba(emailValido), "O email válido deve conter '@'");
        assertFalse(ValidaEmail.validaCaracTerArroba(emailInvalido), "O email inválido não deve conter '@'");
        assertFalse(ValidaEmail.validaCaracTerArroba(emailNulo), "O email nulo não deve conter '@'");
    }

    @Test
    void confirmaEmail() {
        String emailAntigo = "nathanputo1@gmail.com.br";

        assertEquals(emailValido, usuarioDTO.getEmail(), "Os emails devem ser iguais");
        assertNotEquals(usuarioDTO.getEmail(), emailAntigo, "Os emails devem ser diferentes");
        assertNull(emailNulo);
        assertNotNull(usuarioDTO);

        assertAll("Valida email",
                () -> assertEquals(emailValido, usuarioDTO.getEmail()),
                () -> assertTrue(ValidaEmail.validaCaracTerArroba(emailValido))
        );
    }
}

