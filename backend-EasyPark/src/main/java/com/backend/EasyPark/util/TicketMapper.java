// package com.backend.EasyPark.util;

<<<<<<< Updated upstream
import com.backend.EasyPark.enums.TipoTicket;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Component;
=======
// import com.backend.EasyPark.enums.TipoTicket;
// import org.springframework.stereotype.Component;
>>>>>>> Stashed changes

// import com.backend.EasyPark.dto.TicketDTO;
// import com.backend.EasyPark.entities.Ticket;

// import java.time.Duration;
// import java.time.LocalDateTime;

// @Component
// public class TicketMapper {

<<<<<<< Updated upstream
    public Ticket toEntity(TicketDTO dto) {
        Ticket ticket = new Ticket(dto.getId(), dto.getPlacaVeiculo(), dto.getHoraChegada(),
                dto.getHoraSaida(), dto.getTipoTicket(), dto.getTotalHoras(), dto.getValorTotalPagar());
=======
//     public Ticket toEntity(TicketDTO dto) {
//         if (dto == null) {
//             return null;
//         }

//         Ticket ticket = new Ticket();
//         ticket.setId(dto.getId());
//         ticket.setPlacaVeiculo(dto.getPlacaVeiculo());
//         ticket.setHoraChegada(dto.getHoraChegada());
//         ticket.setHoraSaida(dto.getHoraSaida());
//         ticket.setTotalHoras(dto.getTotalHoras());
//         ticket.setValorTotalPagar(dto.getValorTotalPagar());
>>>>>>> Stashed changes

//         return ticket;
//     }

<<<<<<< Updated upstream
    public TicketDTO toDTO(Ticket entity) {
        TicketDTO dto = new TicketDTO(entity.getId(), entity.getPlacaVeiculo(), entity.getHoraChegada(), entity.getHoraSaida()
        ,entity.getTotalHoras(),entity.getTipoTicket(),entity.getValorTotalPagar());
        return dto;
    }



    public void updateEntityFromDTO(Ticket ticket, TicketDTO dto) {
        if (dto == null) {
            return;
        }
        ticket.setPlacaVeiculo(dto.getPlacaVeiculo());
        ticket.setHoraChegada(dto.getHoraChegada());
        ticket.setHoraSaida(dto.getHoraSaida());
        ticket.setTotalHoras(dto.getTotalHoras());
        ticket.setValorTotalPagar(dto.getValorTotalPagar());
    }
}
=======
//     public TicketDTO toDTO(Ticket entity) {
//         if (entity == null) {
//             return null;
//         }

//         return new TicketDTO(
//             entity.getId(),
//             entity.getPlacaVeiculo(),
//             entity.getHoraChegada(),
//             entity.getHoraSaida(),
//             entity.getTotalHoras(),
//             entity.getTipoTicket(),
//             entity.getValorTotalPagar()
//         );
//     }

//     public void updateEntityFromDTO(Ticket ticket, TicketDTO dto) {
//         if (dto == null) {
//             return;
//         }
//         ticket.setPlacaVeiculo(dto.getPlacaVeiculo());
//         ticket.setHoraChegada(dto.getHoraChegada());
//         ticket.setHoraSaida(dto.getHoraSaida());
//         ticket.setTotalHoras(dto.getTotalHoras());
//         ticket.setValorTotalPagar(dto.getValorTotalPagar());
//     }
// }
>>>>>>> Stashed changes
