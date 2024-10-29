package com.backend.EasyPark.util;

import com.backend.EasyPark.enums.TipoTicket;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.TicketDTO;
import com.backend.EasyPark.entities.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TicketMapper {

    public Ticket toEntity(TicketDTO dto) {
        Ticket ticket = new Ticket(dto.getId(), dto.getPlacaVeiculo(), dto.getHoraChegada(),
                dto.getHoraSaida(), dto.getTipoTicket(), dto.getTotalHoras(), dto.getValorTotalPagar());

        return ticket;
    }

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
