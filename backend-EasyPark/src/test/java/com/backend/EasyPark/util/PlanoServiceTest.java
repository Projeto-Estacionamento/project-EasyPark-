package com.backend.EasyPark.util;

import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.dto.PlanoDTO;
import com.backend.EasyPark.model.entities.Plano;
import com.backend.EasyPark.model.enums.TipoPlano;
import com.backend.EasyPark.model.enums.TipoTicket;
import com.backend.EasyPark.model.repository.PlanoRepository;
import com.backend.EasyPark.service.PlanoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlanoServiceTest {

    private PlanoDTO planoDTO;

    @BeforeEach
    void setUp() {
        planoDTO = new PlanoDTO();
        planoDTO.setTipoPlano(TipoPlano.INTEGRAL);
    }

    @Test
    void validaCriacaoDePlano() {
        assertNotNull(planoDTO, "O PlanoDTO não deve ser nulo");
        assertEquals(TipoPlano.INTEGRAL, planoDTO.getTipoPlano(), "O tipo do plano deve ser 'INTEGRAL'");
    }

    @Test
    void validaTipoPlano() {
        PlanoDTO planoMensal = new PlanoDTO();
        planoMensal.setTipoPlano(TipoPlano.MANHA);

        assertNotEquals(planoMensal.getTipoPlano(), planoDTO.getTipoPlano(), "Os tipos de plano devem ser diferentes");
        assertEquals(TipoPlano.MANHA, planoMensal.getTipoPlano(), "O tipo de plano deve ser 'INTEGRAL'");
    }
}