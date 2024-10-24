package com.backend.EasyPark.util.validacao;

import com.backend.EasyPark.dto.EstacionamentoDTO;
import com.backend.EasyPark.entities.*;
import com.backend.EasyPark.enums.TipoVeiculo;
import com.backend.EasyPark.repository.ConfiguracaoSistemaRepository;
import com.backend.EasyPark.repository.TicketRepository;
import com.backend.EasyPark.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

public class ValidacaoVeiculo {

    private VeiculoRepository veiculoRepository;

    //public Veiculo verificarVagaDisponivel(Veiculo veiculo) {
    //  Veiculo vagaDisponivel = verificarVagasDisponiveis(veiculo.getTipoVeiculo());
    // if (vagaDisponivel.getTipoVeiculo() == null) {
    //   throw new EntityNotFoundException("Não há vagas disponíveis para o tipo de veículo." + veiculo.getTipoVeiculo());
    // }
    // return vagaDisponivel;
    // }

    public Optional<Veiculo> verificarSeVeiculoEstaCadastrado(String placaVeiculo) {
        Optional<Veiculo> verificarPlaca = veiculoRepository.findByPlaca(placaVeiculo);
        if (verificarPlaca.isEmpty()) {
            throw new EntityNotFoundException("O veiculo com a placa " + placaVeiculo
                    + " não foi encontrada");
        }
        return verificarPlaca;
    }



    public void validarPlanoVeiculo(Veiculo veiculo) {
        Usuario usuario = veiculo.getUsuario();
        for (Plano plano : usuario.getPlanos()) {
            if (!plano.getTipoPlano().equals(veiculo.getTipoVeiculo())) {
                throw new EntityNotFoundException("O plano não é válido para o tipo de veículo" + veiculo.getTipoVeiculo());
            }
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

