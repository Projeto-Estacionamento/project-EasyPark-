package com.backend.EasyPark.util;

import org.springframework.stereotype.Component;

<<<<<<< Updated upstream
import com.backend.EasyPark.dto.EnderecoDTO;
import com.backend.EasyPark.entities.Endereco;
=======
import com.backend.EasyPark.model.dto.EnderecoDTO;
import com.backend.EasyPark.model.entities.Endereco;
>>>>>>> Stashed changes

@Component
public class EnderecoMapper {

    public static Endereco toEntity(EnderecoDTO dto) {
<<<<<<< Updated upstream
        Endereco endereco = new Endereco(dto.getId(), dto.getCidade(), dto.getEstado(),
                dto.getCep());

=======
        Endereco endereco = new Endereco(dto.getId(), dto.getCidade(), dto.getEstado(), dto.getCep());
>>>>>>> Stashed changes
        return endereco;
    }

    public static EnderecoDTO toDTO(Endereco endereco) {
<<<<<<< Updated upstream
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco.getId(),endereco.getCidade()
                ,endereco.getEstado(),endereco.getCep());


        return enderecoDTO;
    }


=======
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco.getId(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());
        return enderecoDTO;
    }

>>>>>>> Stashed changes
    public static void updateEnderecoFromDTO(Endereco endereco, EnderecoDTO dto) {
        if (dto == null) {
            return;
        }
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
