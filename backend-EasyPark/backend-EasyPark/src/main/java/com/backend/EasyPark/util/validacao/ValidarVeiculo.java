package com.backend.EasyPark.util.validacao;


import com.backend.EasyPark.dto.TicketDTO;
import com.backend.EasyPark.dto.UsuarioPlanoDTO;
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.*;
import com.backend.EasyPark.enums.TipoTicket;

import com.backend.EasyPark.repository.TicketRepository;
import com.backend.EasyPark.repository.VeiculoRepository;
import com.backend.EasyPark.util.TicketMapper;
import com.backend.EasyPark.util.VeiculoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ValidarVeiculo {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    private TicketMapper ticketMapper;
    private VeiculoMapper veiculoMapper;

//    public TicketDTO criarTicketPorPlaca(String placaVeiculo) {
//        try {
//            this.validarPlanoVeiculo(placaVeiculo);
//            return criarTicketMensalista(placaVeiculo);
//
//        } catch (EntityNotFoundException e) {
//            return criarTicketAvulso(placaVeiculo);
//        }
//
//    }

    public TicketDTO criarTicketMensalista(String placaVeiculo) {
        Ticket ticket = new Ticket();
        ticket.setPlacaVeiculo(placaVeiculo);
        ticket.setHoraChegada(LocalDateTime.now());
        ticket.setTipoTicket(TipoTicket.TICKET_MENSALISTA);
        Veiculo veiculo = new Veiculo();
        veiculo.setOcupandoVaga(true);
        veiculoRepository.save(veiculo);
        ticketRepository.save(ticket);

        return ticketMapper.toDTO(ticket);
    }

    public TicketDTO criarTicketAvulso(String placaVeiculo) {
        Ticket ticket = new Ticket();
        ticket.setPlacaVeiculo(placaVeiculo);
        ticket.setHoraChegada(LocalDateTime.now());
        ticket.setTipoTicket(TipoTicket.TICKET_AVULSO);
        ticketRepository.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    public VeiculoDTO buscarVeiculoPorPlaca(String placaVeiculo){
        Veiculo veiculo = veiculoRepository.findByPlaca(placaVeiculo).orElseThrow(() ->
                new EntityNotFoundException("Veículo não encontrado com a placa: " + placaVeiculo));
        return veiculoMapper.toDTO(veiculo);
    }

    public TicketDTO buscarTicketPorPlaca(String placaVeiculo){
        Optional<Ticket> ticketAtivo = ticketRepository.findByPlacaVeiculoAndHoraSaidaIsNull(placaVeiculo);
        if (ticketAtivo.isPresent()) {
            throw new RuntimeException("Já existe um ticket ativo para esta placa");
        }
        return ticketMapper.toDTO(ticketAtivo.get());
    }

    public void validarPlanoVeiculo(String placaVeiculo){
        VeiculoDTO veiculoDTO = buscarVeiculoPorPlaca(placaVeiculo);

        List<UsuarioPlanoDTO> planos = this.planoAssociadoAoUsuarioPlano(veiculoDTO); //plano associado ao usuario que recebe um veiculo

        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataVencimento = planos.get(planos.size()- 1).getDataVencimento();
        if (dataVencimento.isBefore(dataAtual)) {
            throw new EntityNotFoundException("Plano vencido. Favor regularizar o pagamento.");
        }

        if (veiculoDTO.isOcupandoVaga()) {
            throw new EntityNotFoundException("O veículo já está ocupando uma vaga no estacionamento.");
        }

    }
    //Metodo para verificar se o
    private List<UsuarioPlanoDTO> planoAssociadoAoUsuarioPlano(VeiculoDTO veiculo) {
        List<UsuarioPlanoDTO> usuarioPlanoDto = veiculo.getUsuarioDTO().getUsuarioPlanosDto();

        if (usuarioPlanoDto == null || usuarioPlanoDto.isEmpty()) {
            throw new EntityNotFoundException("Não existe plano associado ao usuário");
        }

        for (UsuarioPlanoDTO usuarioPlano : usuarioPlanoDto) {
            if (!usuarioPlano.getPlano().getTipoPlano().equals(usuarioPlanoDto.get(usuarioPlanoDto.size()).getPlano().getTipoPlano())) { //não faz nada
                throw new EntityNotFoundException("O plano não é válido para o tipo de veículo: " + veiculo.getTipoVeiculo());
            }
        }

        return usuarioPlanoDto;
    }

}
