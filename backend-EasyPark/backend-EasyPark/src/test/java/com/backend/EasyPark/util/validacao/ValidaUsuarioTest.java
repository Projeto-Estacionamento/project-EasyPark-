package com.backend.EasyPark.util.validacao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.backend.EasyPark.dto.UsuarioDTO;

public class ValidaUsuarioTest {

    private final ValidacaoUsuario validacaoUsuario = new ValidacaoUsuario();

    @Test
    public void testValidarUsuarioComDadosValidos() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("João Silva");
        usuarioDTO.setEmail("joao.silva@example.com");
        usuarioDTO.setCpf("123.456.789-00");

        // Não deve lançar exceção
        assertDoesNotThrow(() -> validacaoUsuario.validarUsuario(usuarioDTO));
    }

    @Test
    public void testValidarUsuarioComNomeVazio() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("");
        usuarioDTO.setEmail("joao.silva@example.com");
        usuarioDTO.setCpf("123.456.789-00");

        // Deve lançar IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validacaoUsuario.validarUsuario(usuarioDTO);
        });

        assertEquals("Nome do usuário não pode ser vazio", exception.getMessage());
    }

    @Test
    public void testValidarUsuarioComEmailInvalido() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("João Silva");
        usuarioDTO.setEmail("invalid-email");
        usuarioDTO.setCpf("123.456.789-00");

        // Deve lançar IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validacaoUsuario.validarUsuario(usuarioDTO);
        });

        assertEquals("Email inválido", exception.getMessage());
    }

    @Test
    public void testValidarUsuarioComCpfInvalido() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("João Silva");
        usuarioDTO.setEmail("joao.silva@example.com");
        usuarioDTO.setCpf("12345678900"); // Formato inválido

        // Deve lançar IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validacaoUsuario.validarUsuario(usuarioDTO);
        });

        assertEquals("CPF inválido. Use o formato: XXX.XXX.XXX-XX", exception.getMessage());
    }

    @Test
    public void testValidarUsuarioComCpfNulo() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("João Silva");
        usuarioDTO.setEmail("joao.silva@example.com");
        usuarioDTO.setCpf(null); // CPF nulo

        // Deve lançar IllegalArgumentException
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validacaoUsuario.validarUsuario(usuarioDTO);
        });

        assertEquals("CPF inválido. Use o formato: XXX.XXX.XXX-XX", exception.getMessage());
    }
}
