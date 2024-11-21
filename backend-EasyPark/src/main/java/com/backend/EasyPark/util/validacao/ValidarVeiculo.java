package com.backend.EasyPark.util.validacao;



import com.backend.EasyPark.model.dto.VeiculoDTO;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.repository.TicketRepository;
import com.backend.EasyPark.model.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarVeiculo {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;


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

        if (veiculo.getIdUsuarioDTO() == null) {
            throw new EstacionamentoException("O usuário do veículo é obrigatório.");
        }

    }
}
