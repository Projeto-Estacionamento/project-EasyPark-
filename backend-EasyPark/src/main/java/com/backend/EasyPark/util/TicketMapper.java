package com.backend.EasyPark.util;

<<<<<<< Updated upstream
import com.backend.EasyPark.enums.TipoTicket;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.TicketDTO;
import com.backend.EasyPark.entities.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
=======
import org.springframework.stereotype.Component;

import com.backend.EasyPark.model.dto.TicketDTO;
import com.backend.EasyPark.model.entities.Ticket;

import java.util.List;
>>>>>>> Stashed changes

@Component
public class TicketMapper {

<<<<<<< Updated upstream
    public Ticket toEntity(TicketDTO dto) {
        Ticket ticket = new Ticket(dto.getId(), dto.getPlacaVeiculo(), dto.getHoraChegada(),
                dto.getHoraSaida(), dto.getTipoTicket(), dto.getTotalHoras(), dto.getValorTotalPagar());

        return ticket;
    }

    public TicketDTO toDTO(Ticket entity) {
        TicketDTO dto = new TicketDTO(entity.getId(), entity.getPlacaVeiculo(), entity.getHoraChegada(), entity.getHoraSaida()
        ,entity.getTotalHoras(),entity.getTipoTicket(),entity.getValorTotalPagar());
        return dto;
=======
    public static Ticket toEntity(TicketDTO dto) {
        return new Ticket(dto.getId(), dto.getPlacaVeiculo(), dto.getHoraChegada(),
                dto.getHoraSaida(), dto.getTipoTicket(), dto.getTipoVeiculo(), dto.getTotalHoras(), dto.getValorTotalPagar());
    }

    public static TicketDTO toDTO(Ticket entity) {
        return new TicketDTO(entity.getId(), entity.getPlacaVeiculo(), entity.getHoraChegada(), entity.getHoraSaida()
                , entity.getTotalHoras(), entity.getTipoTicket(),entity.getTipoVeiculo(), entity.getValorTotalPagar());
    }

    public static List<TicketDTO> toDtoList(List<Ticket> tickets) {
        return tickets.stream().map(TicketMapper::toDTO).toList();
    }

    public static List<Ticket> toEntityList(List<TicketDTO> dtos) {
        return dtos.stream().map(TicketMapper::toEntity).toList();
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
}
=======


}
>>>>>>> Stashed changes
