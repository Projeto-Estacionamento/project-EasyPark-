package com.backend.EasyPark.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.backend.EasyPark.model.entities.AssinaturaPlano;
import com.backend.EasyPark.model.entities.ConfiguracaoSistema;
import com.backend.EasyPark.model.entities.Veiculo;
import com.backend.EasyPark.model.enums.TipoTicket;
import com.backend.EasyPark.model.enums.TipoVeiculo;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.repository.ConfiguracaoSistemaRepository;
import com.backend.EasyPark.model.repository.VeiculoRepository;
import com.backend.EasyPark.util.validacao.ValidarVeiculo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.EasyPark.model.dto.TicketDTO;
import com.backend.EasyPark.model.entities.Ticket;
import com.backend.EasyPark.model.repository.TicketRepository;
import com.backend.EasyPark.util.TicketMapper;
// import com.backend.EasyPark.service.ConfiguracaoSistemaService;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ConfiguracaoSistemaService configuracaoSistemaService;
    private final ValidarVeiculo validarVeiculo;
    private final VeiculoRepository veiculoRepository;
    private final ConfiguracaoSistemaRepository configuracaoSistemaRepository;


    @Autowired
    public TicketService(TicketRepository ticketRepository,
                         ConfiguracaoSistemaService configuracaoSistemaService,
                         ValidarVeiculo validarVeiculo,
                         VeiculoRepository veiculoRepository, ConfiguracaoSistemaRepository configuracaoSistemaRepository) {
        this.ticketRepository = ticketRepository;
        this.configuracaoSistemaService = configuracaoSistemaService;
        this.validarVeiculo = validarVeiculo;
        this.veiculoRepository = veiculoRepository;
        this.configuracaoSistemaRepository = configuracaoSistemaRepository;
    }
    public TicketDTO criarTicket(TicketDTO ticket) throws EstacionamentoException {
        // Verifica se os dados básicos são válidos
        if (ticket == null || ticket.getPlacaVeiculo() == null) {
            throw new EstacionamentoException("Ticket ou placa do veículo não podem ser nulos");
        }

        Optional<Ticket> ticketExistente = ticketRepository.findByPlacaVeiculo(ticket.getPlacaVeiculo());

        if (ticketExistente.isPresent()) {
            throw new EstacionamentoException("Já existe um veículo estacionado com esta placa: " + ticket.getPlacaVeiculo());
        }

        LocalTime horaAtual = LocalTime.now();
        //Busca os veículos com assinatura válida
        List<Veiculo> veiculos = veiculoRepository.buscarVeiculoComAssinaturaValida(ticket.getPlacaVeiculo());

        //Criando um ticket manualmente
        Ticket ticketEntity = new Ticket();
        ticketEntity.setPlacaVeiculo(ticket.getPlacaVeiculo());
        ticketEntity.setTipoVeiculo(ticket.getTipoVeiculo());
        ticketEntity.setHoraChegada(LocalDateTime.now());

        //Se encontrar veículos com assinatura válida
        if (!veiculos.isEmpty()) {
            Veiculo veiculo = veiculos.get(0);  //Considerando que pegamos o primeiro veículo válido

            boolean planoValidoEncontrado = false;

            // Verifica as assinaturas do usuário para encontrar um plano válido
            for (AssinaturaPlano assinatura : veiculo.getUsuario().getAssinaturas()) {
                if (assinatura.getPlano().getTipoPlano().contemHorario(horaAtual)) {
                    // Se encontrar um plano válido, define o ticket como mensalista
                    ticketEntity.setTipoTicket(TipoTicket.TICKET_MENSALISTA);
                    veiculo.setOcupandoVaga(true);
                    veiculoRepository.save(veiculo);  // Salva o estado do veículo
                    planoValidoEncontrado = true;
                    break;  // Para o loop ao encontrar o primeiro plano válido
                }
            }

            // Se nenhum plano válido foi encontrado, configura o ticket como avulso
            if (!planoValidoEncontrado) {
                ticketEntity.setTipoTicket(TipoTicket.TICKET_AVULSO);
            }
        } else {
            // Se nenhum veículo com assinatura válida for encontrado, cria ticket avulso
            ticketEntity.setTipoTicket(TipoTicket.TICKET_AVULSO);
        }

        // Tenta salvar o ticket no banco de dados
        try {
            return TicketMapper.toDTO(ticketRepository.save(ticketEntity));
        } catch (Exception e) {
            throw new EstacionamentoException("Erro ao criar o ticket: " + e.getMessage());
        }
    }


    public TicketDTO buscarTicketPorId(Integer id) {
        return ticketRepository.findById(id)
                .map(TicketMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Ticket não encontrado"));
    }

    public List<TicketDTO> listarTickets() {
        return ticketRepository.findAll().stream()
                .map(TicketMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TicketDTO finalizarTicket(Integer id) {
        //Finaliza um ticket, calculando o tempo total e o valor a pagar
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket não encontrado"));

        ticket.setHoraSaida(LocalDateTime.now());
        ticket.setTotalHoras(Duration.between(ticket.getHoraChegada(), ticket.getHoraSaida()));

        BigDecimal valorTotal = calcularValorTicket(TicketMapper.toDTO(ticket));
        ticket.setValorTotalPagar(valorTotal.doubleValue());

        // Salva o ticket com o valor calculado
        Ticket updatedTicket = ticketRepository.save(ticket);

        // Caso for mensalista ou avulso, trocamos o status de ocupação do veículo
        if (ticket.getTipoTicket().equals(TipoTicket.TICKET_MENSALISTA) || ticket.getTipoTicket().equals(TipoTicket.TICKET_AVULSO)) {
            // Quando for mensalista, obrigatório ter um veículo associado ao usuário
            Optional<Veiculo> veiculo = veiculoRepository.findByPlaca(ticket.getPlacaVeiculo());
            if (veiculo.isPresent()) {
                veiculo.get().setOcupandoVaga(false);
                veiculoRepository.save(veiculo.get());
            }
        }

        //Exclui o ticket após finalização para evitar o acúmulo de dados
        ticketRepository.delete(ticket);

        //Retorna o DTO do ticket finalizado, com os dados atualizados
        return TicketMapper.toDTO(updatedTicket);
    }


    public BigDecimal calcularValorTicket(TicketDTO ticket) {
        // 1. Verifica se o ticket é de um cliente mensalista ou se a permanência foi inferior a 15 minutos
        boolean isMenosDe15Minutos = ticket.getTotalHoras().toMinutes() < 15;
        if (isMenosDe15Minutos || ticket.getTipoTicket().equals(TipoTicket.TICKET_MENSALISTA)) {
            return BigDecimal.ZERO; // Isenção de pagamento
        }

        //Se for ticket avulso, procura a placa e determina o tipo de veículo
        BigDecimal valorHora = BigDecimal.ZERO;

        if (ticket.getTipoTicket().equals(TipoTicket.TICKET_AVULSO)) {
            //PARA O TICKET AVULSO, NAO PRECISA BUSCAR NO VEICULO, AVULSO NAO CADASTRA VEICULOS

            //VERIFICAR SE EXISTE UMA CONFIGURACAO NO SISTEMA
            ConfiguracaoSistema configuracao = configuracaoSistemaRepository.findTopByOrderByIdDesc()
                    .orElseThrow(() -> new RuntimeException("Configuração do sistema não encontrada"));


            //AQUI TBM DETERMINAR O VALOR DA HORA POR TIPO DO VEICULO
            if (ticket.getTipoVeiculo() == TipoVeiculo.MOTO) {
                valorHora = BigDecimal.valueOf(configuracao.getValorHoraMoto());
            } else if (ticket.getTipoVeiculo() == TipoVeiculo.CARRO) {
                valorHora = BigDecimal.valueOf(configuracao.getValorHoraCarro());
            }
        } else {
            //PARA O TICKET MENSALISTA PESQUISAR O TIPO PARA VERIFICAR AS VALIDAÇOES
            String placaVeiculo = ticket.getPlacaVeiculo();

            if (placaVeiculo != null && !placaVeiculo.isEmpty()) {

                Veiculo veiculo = veiculoRepository.findByPlaca(placaVeiculo)
                        .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

                TipoVeiculo tipoVeiculo = veiculo.getTipoVeiculo();

                List<AssinaturaPlano> tipoPlano = veiculo.getUsuario().getAssinaturas();  // Assumindo que existe um plano ativo

                for (AssinaturaPlano assinaturaPlano : tipoPlano) {

                    ConfiguracaoSistema configuracao = configuracaoSistemaRepository.findTopByOrderByIdDesc()
                            .orElseThrow(() -> new RuntimeException("Configuração do sistema não encontrada"));

                    //DETERMINAR O VALOR DO VEICULO PELO TIPOVEICULO
                    if (tipoVeiculo == TipoVeiculo.MOTO) {
                        valorHora = BigDecimal.valueOf(configuracao.getValorHoraMoto());
                    } else if (tipoVeiculo == TipoVeiculo.CARRO) {
                        valorHora = BigDecimal.valueOf(configuracao.getValorHoraCarro());
                    }

                    //VERIFICAR PARA VER SE PASSOU O HORARIO DO PLANO
                    LocalTime horaLimitePlano = assinaturaPlano.getPlano().getTipoPlano().getHoraFim();  // Hora final do plano

                    if (ticket.getHoraSaida().toLocalTime().isAfter(horaLimitePlano)) {
                        //AQUI, SE PASSAR DO HORARIO DO PLANO ELE PAGA A DIFERENÇA POR VEICULO
                        Duration horasExtras = Duration.between(horaLimitePlano, ticket.getHoraSaida().toLocalTime());
                        long horasExtrasCalculadas = horasExtras.toHours();

                        //METODO PARA HORAS QUANDO TIVER EM MINUTOS
                        if (horasExtras.toMinutesPart() > 0) {
                            horasExtrasCalculadas++;
                        }

                        //Adiciona o valor das horas extras ao valor do ticket
                        return valorHora.multiply(BigDecimal.valueOf(horasExtrasCalculadas));
                    }
                }
            } else {
                //Se o ticket não tem placa e não é avulso, consideramos erro
                throw new RuntimeException("Ticket mensalista precisa de um veículo cadastrado.");
            }
        }

        //Se for ticket avulso e dentro do horário, calcula o valor total a ser pago
        long horasEstacionado = ticket.getTotalHoras().toHours();

        //Arredonda para cima qualquer fração de hora
        if (ticket.getTotalHoras().toMinutesPart() > 0) {
            horasEstacionado++;
        }

        //Calcula o valor total a ser pago
        return valorHora.multiply(BigDecimal.valueOf(horasEstacionado));
    }







}


