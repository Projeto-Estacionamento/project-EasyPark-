package com.backend.EasyPark.util;

import org.springframework.stereotype.Component;

import com.backend.EasyPark.dto.TicketDTO;
import com.backend.EasyPark.entities.Ticket;

@Component
public class TicketMapper {

    public Ticket toEntity(TicketDTO dto) {
        if (dto == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        ticket.setPlacaVeiculo(dto.getPlacaVeiculo());
        ticket.setHoraChegada(dto.getHoraChegada());
        ticket.setHoraSaida(dto.getHoraSaida());
        ticket.setTotalHoras(dto.getTotalHoras());
        ticket.setValorTotalPagar(dto.getValorTotalPagar());

        return ticket;
    }

    public TicketDTO toDTO(Ticket entity) {
        if (entity == null) {
            return null;
        }

        return new TicketDTO(
            entity.getId(),
            entity.getPlacaVeiculo(),
            entity.getHoraChegada(),
            entity.getHoraSaida(),
            entity.getTotalHoras(),
            entity.getValorTotalPagar()
        );
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
