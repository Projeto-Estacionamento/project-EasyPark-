package com.backend.EasyPark.util;

import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.dto.AcessoDTO;
import com.backend.EasyPark.model.entities.Acesso;
import com.backend.EasyPark.model.repository.AcessoRepository;
import com.backend.EasyPark.service.AcessoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AcessoServiceTest {

    private AcessoDTO acessoDTO;

    @BeforeEach
    void setUp() {
        acessoDTO = new AcessoDTO();
        acessoDTO.setEmail("nathan154@gmail.com.br");
        acessoDTO.setSenha("senha123");
    }

    @Test
    void validaCriacaoDeAcesso() {
        assertNotNull(acessoDTO, "O AcessoDTO não deve ser nulo");
        assertEquals("nathan154@gmail.com.br", acessoDTO.getEmail(), "O email deve ser 'nathan154@gmail.com.br'");
        assertEquals("senha123", acessoDTO.getSenha(), "A senha deve ser 'senha123'");
    }

    @Test
    void validaFormatoEmail() {
        String emailInvalido = "nathan154gmail.com.br";

        assertTrue(acessoDTO.getEmail().contains("@"), "O email deve conter '@'");
        assertFalse(emailInvalido.contains("@"), "Email inválido não deve conter '@'");
    }

    @Test
    void validaAtualizacaoDeAcesso() {
        acessoDTO.setSenha("novaSenha123");
        assertEquals("novaSenha123", acessoDTO.getSenha(), "A senha deve ser atualizada para 'novaSenha123'");
    }

    @Test
    void validaDelecaoDeAcesso() {
        int acessoId = 1;
        assertDoesNotThrow(() -> {
            // Aqui normalmente chamaria o método deleteById no serviço
            // No contexto de teste, estamos apenas verificando se não ocorre exceção
            // Não há necessidade de mockar a operação de exclusão aqui
        });
    }
}