package com.backend.EasyPark.util.validacao;


import com.backend.EasyPark.dto.EstacionamentoDTO;
import com.backend.EasyPark.dto.TicketDTO;
import com.backend.EasyPark.entities.*;
import com.backend.EasyPark.enums.TipoTicket;

import com.backend.EasyPark.repository.TicketRepository;
import com.backend.EasyPark.repository.VeiculoRepository;
import com.backend.EasyPark.util.TicketMapper;
import jakarta.persistence.EntityNotFoundException;


import java.time.LocalDateTime;
import java.util.Optional;

public class ValidacaoVeiculo {
    private  TicketRepository ticketRepository;
    private  VeiculoRepository veiculoRepository;
    private  TicketMapper ticketMapper;

    //public Veiculo verificarVagaDisponivel(Veiculo veiculo) {
    //  Veiculo vagaDisponivel = verificarVagasDisponiveis(veiculo.getTipoVeiculo());
    // if (vagaDisponivel.getTipoVeiculo() == null) {
    //   throw new EntityNotFoundException("Não há vagas disponíveis para o tipo de veículo." + veiculo.getTipoVeiculo());
    // }
    // return vagaDisponivel;
    // }

    public void verificarSeVeiculoEstaCadastrado(TicketDTO ticketDTO) {
        Optional<Veiculo> verificarVeiculo = veiculoRepository.findByPlaca(ticketDTO.getPlacaVeiculo());

        if (verificarVeiculo.isEmpty()) {
            criarTicketAvulso(ticketDTO);
        } else {
            verificarVeiculo.ifPresent(this::validarPlanoVeiculo);

            criarTicketMensalista(ticketDTO);
        }

    }

    private Ticket criarTicketMensalista(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        Veiculo veiculo = new Veiculo();
        ticket.setPlacaVeiculo(ticket.getPlacaVeiculo());
        ticket.setHoraChegada(LocalDateTime.now());
        ticket.setTipoTicket(TipoTicket.TICKET_MENSALISTA);
        ticketRepository.save(ticket);

        veiculo.setOcupandoVaga(true);
        veiculoRepository.save(veiculo);

        return ticket;
    }

    private Ticket criarTicketAvulso(TicketDTO ticketDTO) {
        Veiculo veiculo = new Veiculo();
        Ticket ticket =  ticketMapper.toEntity(ticketDTO);
        ticket.setPlacaVeiculo(ticketDTO.getPlacaVeiculo());
        ticket.setHoraChegada(LocalDateTime.now());
        ticket.setTipoTicket(TipoTicket.TICKET_AVULSO);
        veiculo.setOcupandoVaga(true);
        ticketRepository.save(ticket);

        return ticket;
    }

    private void validarPlanoVeiculo(Veiculo veiculo) {
        Usuario usuario = veiculo.getUsuario();
        for (Plano plano : usuario.getPlanos()) {
            if (!plano.getTipoPlano().equals(veiculo.getTipoVeiculo())) {
                throw new EntityNotFoundException("O plano não é válido para o tipo de veículo" + veiculo.getTipoVeiculo());
            }
        }
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataPagamento = usuario.getPlanos().getLast().getDataPagamento();
        if (dataPagamento == null || dataPagamento.plusDays(30).isBefore(dataAtual)) {
           throw new EntityNotFoundException("Plano vencido. Favor regularizar o pagamento.");
        }

        if (veiculo.isOcupandoVaga()) {
            throw new EntityNotFoundException("O veículo já está ocupando uma vaga no estacionamento.");
        }
    }

}
//if (veiculo.get().getPlaca() == null) {
//            return new EstacionamentoDTO(false, criarTicketAvulso(placaVeiculo), null);
//        }
//
//        EstacionamentoDTO validacaoPlano = validarPlanoVeiculo(veiculo.get());
//        if (!validacaoPlano.isSucesso()) {
//            return validacaoPlano;
//        }
//
//        EstacionamentoDTO validacaoVaga = verificarVagaDisponivel(veiculo.get());
//        if (!validacaoVaga.isSucesso()) {
//            return validacaoVaga;
//        }
//
//        Ticket ticket = criarTicketMensalista(veiculo.get());
//        return new EstacionamentoDTO(true, "Entrada registrada com sucesso.", ticket);
//    }

