package com.backend.EasyPark.service;

import com.backend.EasyPark.dto.AssinaturaPlanoDTO;
import com.backend.EasyPark.entities.AssinaturaPlano;
import com.backend.EasyPark.entities.Plano;
import com.backend.EasyPark.entities.Usuario;
import com.backend.EasyPark.enums.TipoPlano;
import com.backend.EasyPark.repository.AssinaturaPlanoRepository;
import com.backend.EasyPark.repository.PlanoRepository;
import com.backend.EasyPark.repository.UsuarioRepository;
import com.backend.EasyPark.util.AssinaturaPlanoMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssinaturaPlanoService {

    private final AssinaturaPlanoRepository assinaturaPlanoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanoRepository planoRepository;

    @Transactional(readOnly = true)
    public List<AssinaturaPlanoDTO> findAll() {
        return AssinaturaPlanoMapper.toDTOList(assinaturaPlanoRepository.findAll());
    }

    @Transactional(readOnly = true)
    public AssinaturaPlanoDTO findById(Integer id) {
        return AssinaturaPlanoMapper.toDTO(assinaturaPlanoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada")));
    }

    @Transactional
    public AssinaturaPlanoDTO create(AssinaturaPlanoDTO dto) {
        // Verifica se o usuário já possui uma assinatura ativa
        AssinaturaPlano entity = AssinaturaPlanoMapper.toEntity(dto);
        // Busca e valida o usuário
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioDTO().getId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        entity.setUsuario(usuario);

        // Busca e valida o plano
        Plano plano = planoRepository.findById(dto.getPlanoDTO().getId())
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));
        entity.setPlano(plano);

        // Define a data atual como data de pagamento
        LocalDateTime dataPagamento = LocalDateTime.now();
        entity.setDataPagamento(dataPagamento);

        // Define a data de vencimento como 30 dias após o pagamento
        LocalDateTime dataVencimento = dataPagamento.plusDays(30);
        entity.setDataVencimento(dataVencimento);

        // Marca a assinatura como ativa
        entity.setAtivo(true);

        // Valida o tipo do plano
        if (!isTipoPlanoValido(plano.getTipoPlano())) {
            throw new IllegalArgumentException("Tipo de plano inválido. Deve ser INTEGRAL, MANHA ou TARDE");
        }

        return AssinaturaPlanoMapper.toDTO(assinaturaPlanoRepository.save(entity));
    }

    private boolean isTipoPlanoValido(TipoPlano tipoPlano) {
        return tipoPlano == TipoPlano.INTEGRAL ||
                tipoPlano == TipoPlano.MANHA ||
                tipoPlano == TipoPlano.TARDE;
    }

    // Método para verificar se o horário atual está dentro do período do plano
    public boolean isHorarioPermitido(TipoPlano tipoPlano) {
        int horaAtual = LocalDateTime.now().getHour();

        return switch (tipoPlano) {
            case INTEGRAL -> true; // Pode acessar em qualquer horário
            case MANHA -> horaAtual >= 6 && horaAtual < 12; // 6h às 12h
            case TARDE -> horaAtual >= 12 && horaAtual < 18; // 12h às 18h
        };
    }

    @Transactional
    public AssinaturaPlanoDTO update(Integer id, AssinaturaPlanoDTO dto) {
        AssinaturaPlano entity = assinaturaPlanoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada"));

        AssinaturaPlanoMapper.updateEntityFromDTO(entity, dto);

        if (dto.getUsuarioDTO() != null && dto.getUsuarioDTO().getId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioDTO().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
            entity.setUsuario(usuario);
        }

        if (dto.getPlanoDTO() != null && dto.getPlanoDTO().getId() != null) {
            Plano plano = planoRepository.findById(dto.getPlanoDTO().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));
            entity.setPlano(plano);
        }

        return AssinaturaPlanoMapper.toDTO(assinaturaPlanoRepository.save(entity));
    }

    @Transactional
    public void delete(Integer id) {
        if (!assinaturaPlanoRepository.existsById(id)) {
            throw new EntityNotFoundException("Assinatura não encontrada");
        }
        assinaturaPlanoRepository.deleteById(id);
    }
}
