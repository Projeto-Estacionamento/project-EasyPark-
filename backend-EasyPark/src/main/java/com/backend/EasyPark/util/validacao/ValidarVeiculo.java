package com.backend.EasyPark.util.validacao;



import com.backend.EasyPark.dto.PlanoDTO;
import com.backend.EasyPark.dto.TicketDTO;
import com.backend.EasyPark.dto.VeiculoDTO;
import com.backend.EasyPark.entities.*;
import com.backend.EasyPark.enums.TipoTicket;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.repository.TicketRepository;
import com.backend.EasyPark.repository.VeiculoRepository;
import com.backend.EasyPark.util.TicketMapper;
import com.backend.EasyPark.util.VeiculoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ValidarVeiculo {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
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

   /* public TicketDTO criarTicketMensalista(String placaVeiculo) {
        Ticket ticket = new Ticket();
        ticket.setPlacaVeiculo(placaVeiculo);
        ticket.setHoraChegada(LocalDateTime.now());
        ticket.setTipoTicket(TipoTicket.TICKET_MENSALISTA);
        Veiculo veiculo = new Veiculo();
        veiculo.setOcupandoVaga(true);
        veiculoRepository.save(veiculo);
        ticketRepository.save(ticket);

        return TicketMapper.toDTO(ticket);
    }*/

    public TicketDTO criarTicketAvulso(String placaVeiculo) {
        Ticket ticket = new Ticket();
        ticket.setPlacaVeiculo(placaVeiculo);
        ticket.setHoraChegada(LocalDateTime.now());
        ticket.setTipoTicket(TipoTicket.TICKET_AVULSO);
        ticketRepository.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    public VeiculoDTO buscarVeiculoPorPlaca(String placaVeiculo) throws EstacionamentoException {
       Veiculo veiculo = veiculoRepository.findByPlaca(placaVeiculo);
        if (veiculo == null || veiculo.equals("")) {}
        return veiculoMapper.toDTO(veiculo);
    }

    public TicketDTO buscarTicketPorPlaca(String placaVeiculo) throws EstacionamentoException {
        Optional<Ticket> ticketAtivo = ticketRepository.findByPlacaVeiculoAndHoraSaidaIsNull(placaVeiculo);
        if (ticketAtivo.isPresent()) {
            throw new EstacionamentoException("Já existe um ticket ativo para esta placa");
        }
        return ticketMapper.toDTO(ticketAtivo.get());
    }

    public void validarExpiracaoPagamentoIsOcupando(String placaVeiculo) throws EstacionamentoException {
        VeiculoDTO veiculoDTO = buscarVeiculoPorPlaca(placaVeiculo);
        //plano associado ao usuario que recebe um veiculo
        List<PlanoDTO> planoDTOs = veiculoDTO.getUsuarioDTO().getPlanosDTO();
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataVencimento = planoDTOs.get(planoDTOs.size() - 1).getDataVencimento();
        if (dataVencimento.isBefore(dataAtual)) {
            throw new EstacionamentoException("Plano vencido. Favor regularizar o pagamento.");
        }
        if (veiculoDTO.isOcupandoVaga()) {
            throw new EstacionamentoException("O veículo já está ocupando uma vaga no estacionamento.");
        }
    }

    //Metodo para verificar se o
    /*private VeiculoDTO planoAssociadoAoUsuarioPlano(VeiculoDTO veiculo) {

        if (veiculo.getUsuarioDTO().getUsuarioPlanosDto() == null || veiculo.getUsuarioDTO().getUsuarioPlanosDto().isEmpty()) {
            throw new EntityNotFoundException("Não existe plano associado ao usuário");
        }

        if (veiculo.getTipoVeiculo().equals(veiculo.getUsuarioDTO().getUsuarioPlanosDto().get(0).getPlano().getTipoPlano())) { //não faz nada
                throw new EntityNotFoundException("O plano não é válido para o tipo de veículo: " + veiculo.getTipoVeiculo());

        }
        return null;

    }*/

   /* public VeiculoDTO verificarSeExisteUmPlano(String placaVeiculo) throws EstacionamentoException {// Buscando o veículo pela placa
        VeiculoDTO veiculo = buscarVeiculoPorPlaca(placaVeiculo);

        // Verifica se o usuário possui planos associados
        if (veiculo.getUsuarioDTO().getUsuarioPlanosDto() == null || veiculo.getUsuarioDTO().getUsuarioPlanosDto().isEmpty()) {
            throw new EntityNotFoundException("Não existe plano associado ao usuário");
        }
        return veiculo;
    }
*/
  /*  public VeiculoDTO verificarOtipoPlanoIgualTipoVeiculo(String placaVeiculo) throws EstacionamentoException {
        VeiculoDTO veiculo = buscarVeiculoPorPlaca(placaVeiculo);
        // Loop para verificar se algum plano é compatível com o tipo de veículo
        for (UsuarioPlanoDTO usuarioPlanoDto : veiculo.getUsuarioDTO().getUsuarioPlanosDto()) {
            // Verifica se o tipo do plano do usuário é igual ao tipo do veículo
            if (!usuarioPlanoDto.getPlano().getTipoPlano().equals(veiculo.getTipoVeiculo())) {
                throw new EntityNotFoundException("O veículo não é compatível com o tipo de plano: " + usuarioPlanoDto.getPlano().getTipoPlano()
                        + ". Tipo do veículo: " + veiculo.getTipoVeiculo());
            }
        }
        return veiculo;
    }*/

    public void validarCampoVeiculo(VeiculoDTO veiculo) throws EstacionamentoException{
        // Valida o campo 'placa'
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new EstacionamentoException("A placa do veículo é obrigatória.");
        } else if (veiculo.getPlaca().length() != 7 || !veiculo.getPlaca().matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")) {
            throw new EstacionamentoException("A placa deve conter 7 caracteres e estar no formato padrão (exemplo: ABC1234 ou ABC1D23).");
        }

        if (veiculo.getTipoVeiculo() == null) {
            throw new EstacionamentoException("O tipo de veículo é obrigatório.");
        }

        if (veiculo.getFabricanteDTO() == null) {
            throw new EstacionamentoException("O fabricante do veículo é obrigatório.");
        }

        if (veiculo.getUsuarioDTO() == null) {
            throw new EstacionamentoException("O usuário do veículo é obrigatório.");
        }

    }
}
