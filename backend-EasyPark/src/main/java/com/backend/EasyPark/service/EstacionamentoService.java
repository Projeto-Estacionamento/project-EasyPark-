// package com.backend.EasyPark.service;

// import com.backend.EasyPark.dto.EstacionamentoDTO;
// import com.backend.EasyPark.entities.*;
// import com.backend.EasyPark.enums.TipoTicket;
// import com.backend.EasyPark.enums.TipoVeiculo;
// import com.backend.EasyPark.repository.ConfiguracaoSistemaRepository;
// import com.backend.EasyPark.repository.TicketRepository;
// import com.backend.EasyPark.repository.VeiculoRepository;
// import jakarta.persistence.EntityNotFoundException;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.Optional;

// @Service
// public class EstacionamentoService {

//     @Autowired
//     private VeiculoRepository veiculoRepository;

//     @Autowired
//     private TicketRepository ticketRepository;

//     @Autowired
//     private ConfiguracaoSistemaRepository configuracaoSistemaRepository;


//     public EstacionamentoDTO RegistrarEntradaDTO(String placaVeiculo) {
//         Optional<Veiculo> veiculo = veiculoRepository.findByPlaca(placaVeiculo);

//     if (veiculo.isEmpty()){
//         throw new EntityNotFoundException("O veiculo com a placa " + veiculo.get().getPlaca()
//                 + " não foi encontrada" );
//     }

//     if (veiculo.get().getPlaca() == null) {
//         return new EstacionamentoDTO(false, criarTicketAvulso(placaVeiculo), null);
//     }

//         EstacionamentoDTO validacaoPlano = validarPlanoVeiculo(veiculo.get());
//         if (!validacaoPlano.isSucesso()) {
//             return validacaoPlano;
//         }

//         EstacionamentoDTO validacaoVaga = verificarVagaDisponivel(veiculo.get());
//         if (!validacaoVaga.isSucesso()) {
//             return validacaoVaga;
//         }

//         Ticket ticket = criarTicketMensalista(veiculo.get());
//         return new EstacionamentoDTO(true, "Entrada registrada com sucesso.", ticket);
//     }

//     private EstacionamentoDTO validarPlanoVeiculo(Veiculo veiculo) {
//         Usuario usuario = veiculo.getUsuario();
//         Plano plano = usuario.getPlanos().stream()
//                 .filter(p -> p.getTipoPlano().name().equals(veiculo.getTipoVeiculo().name()))
//                 .findFirst().orElse(null);

//         if (plano == null) {
//             return new EstacionamentoDTO(false, "Usuário não possui plano compatível com o tipo de veículo.", null);
//         }

//         LocalDateTime dataAtual = LocalDateTime.now();
//         LocalDateTime dataPagamento = plano.getDataPagamento();
//         if (dataPagamento == null || dataPagamento.plusDays(30).isBefore(dataAtual)) {
//             return new EstacionamentoDTO(false, "Plano vencido. Favor regularizar o pagamento.", null);
//         }

//         if (veiculo.isOcupandoVaga()) {
//             return new EstacionamentoDTO(false, "O veículo já está ocupando uma vaga no estacionamento.", null);
//         }

//         return new EstacionamentoDTO(true, "Plano válido.", null);
//     }

//     private EstacionamentoDTO verificarVagaDisponivel(Veiculo veiculo) {
//         boolean vagaDisponivel = verificarVagasDisponiveis(veiculo.getTipoVeiculo());
//         if (!vagaDisponivel) {
//             return new EstacionamentoDTO(false, "Não há vagas disponíveis para o tipo de veículo.", null);
//         }

//         return new EstacionamentoDTO(true, "Vaga disponível.", null);
//     }

//     private String criarTicketAvulso(String placaVeiculo) {
//         Ticket ticket = new Ticket();
//         ticket.setPlacaVeiculo(placaVeiculo);
//         ticket.setHoraChegada(LocalDateTime.now());
//         ticket.setTipoTicket(TipoTicket.TICKET_AVULSO);
//         ticketRepository.save(ticket);

//         return "Entrada registrada como avulso.";
//     }

//     private Ticket criarTicketMensalista(Veiculo veiculo) {
//         Ticket ticket = new Ticket();
//         ticket.setPlacaVeiculo(veiculo.getPlaca());
//         ticket.setHoraChegada(LocalDateTime.now());
//         ticket.setTipoTicket(TipoTicket.TICKET_MENSALISTA);
//         ticketRepository.save(ticket);

//         veiculo.setOcupandoVaga(true);
//         veiculoRepository.save(veiculo);

//         return ticket;
//     }

//     private boolean verificarVagasDisponiveis(TipoVeiculo tipoVeiculo) {
//         ConfiguracaoSistema configuracao = configuracaoSistemaRepository.findById(1L).orElseThrow();

//         if (tipoVeiculo == TipoVeiculo.CARRO) {
//             return configuracao.getQtdCarro() > ticketRepository.countByTipoVeiculo(TipoVeiculo.CARRO);
//         } else {
//             return configuracao.getQtdMoto() > ticketRepository.countByTipoVeiculo(TipoVeiculo.MOTO);
//         }
//     }



// }
