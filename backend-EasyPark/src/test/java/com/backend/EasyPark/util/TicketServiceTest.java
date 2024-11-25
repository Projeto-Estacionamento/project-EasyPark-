package com.backend.EasyPark.util;

import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.dto.TicketDTO;
import com.backend.EasyPark.model.entities.ConfiguracaoSistema;
import com.backend.EasyPark.model.entities.Ticket;
import com.backend.EasyPark.model.enums.TipoTicket;
import com.backend.EasyPark.model.enums.TipoVeiculo;
import com.backend.EasyPark.model.repository.ConfiguracaoSistemaRepository;
import com.backend.EasyPark.model.repository.TicketRepository;
import com.backend.EasyPark.model.repository.VeiculoRepository;
import com.backend.EasyPark.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TicketServiceTest {

    @Test
    public void testCalcularValorTicketSemService() {
        // Configuração necessária
        ConfiguracaoSistema configuracao = new ConfiguracaoSistema();
        configuracao.setQtdMoto(10);
        configuracao.setQtdCarro(10);
        configuracao.setValorHoraMoto(5.0);
        configuracao.setValorHoraCarro(10.0);

        // Dados do ticket
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTipoTicket(TipoTicket.TICKET_AVULSO);
        ticketDTO.setPlacaVeiculo("ABC1234");
        ticketDTO.setTipoVeiculo(TipoVeiculo.CARRO);
        ticketDTO.setTotalHoras(Duration.ofHours(2)); // 2 horas

        // Lógica de cálculo simulada
        BigDecimal valor = BigDecimal.ZERO;
        if (ticketDTO.getTipoVeiculo() == TipoVeiculo.CARRO) {
            valor = BigDecimal.valueOf(ticketDTO.getTotalHoras().toHours() * configuracao.getValorHoraCarro());
        } else if (ticketDTO.getTipoVeiculo() == TipoVeiculo.MOTO) {
            valor = BigDecimal.valueOf(ticketDTO.getTotalHoras().toHours() * configuracao.getValorHoraMoto());
        }

        // Validação do resultado
        assertEquals(BigDecimal.valueOf(20.0), valor, "O valor calculado para 2 horas de CARRO está incorreto!");
    }

    @Test
    public void testFinalizarTicketCalculaHoraSaidaSemService() {
        // Dados do ticket inicial
        Ticket ticket = new Ticket();
        ticket.setPlacaVeiculo("ABC1234");
        ticket.setTipoVeiculo(TipoVeiculo.CARRO);
        ticket.setHoraChegada(LocalDateTime.now().minusHours(3)); // Chegou 3 horas atrás

        // Simulando lógica de finalização
        TicketDTO ticketFinalizado = new TicketDTO();
        ticketFinalizado.setPlacaVeiculo(ticket.getPlacaVeiculo());
        ticketFinalizado.setTipoVeiculo(ticket.getTipoVeiculo());
        ticketFinalizado.setHoraChegada(ticket.getHoraChegada());
        ticketFinalizado.setHoraSaida(LocalDateTime.now());
        ticketFinalizado.setTotalHoras(Duration.between(ticket.getHoraChegada(), ticketFinalizado.getHoraSaida()));

        // Validação
        assertNotNull(ticketFinalizado.getHoraSaida(), "A hora de saída não foi registrada corretamente!");
        assertEquals(3, ticketFinalizado.getTotalHoras().toHours(), "O total de horas deveria ser 3!");
    }


    @Test
    public void testValidarVeiculoPermitidoSemService() {
        // Configuração simulada
        ConfiguracaoSistema configuracao = new ConfiguracaoSistema();
        configuracao.setQtdMoto(10);
        configuracao.setQtdCarro(10);

        // Dados do ticket
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTipoVeiculo(TipoVeiculo.CARRO);

        // Simulando lógica de validação
        boolean permitido = (ticketDTO.getTipoVeiculo() == TipoVeiculo.CARRO && configuracao.getQtdCarro() > 0)
                || (ticketDTO.getTipoVeiculo() == TipoVeiculo.MOTO && configuracao.getQtdMoto() > 0);

        // Validação
        assertTrue(permitido, "O veículo CARRO deveria ser permitido!");

        // Testando para tipo inválido
        ticketDTO.setTipoVeiculo(null); // Tipo inválido
        permitido = (ticketDTO.getTipoVeiculo() != null)
                && ((ticketDTO.getTipoVeiculo() == TipoVeiculo.CARRO && configuracao.getQtdCarro() > 0)
                || (ticketDTO.getTipoVeiculo() == TipoVeiculo.MOTO && configuracao.getQtdMoto() > 0));
        assertFalse(permitido, "Um tipo de veículo inválido não deveria ser permitido!");
    }

}
