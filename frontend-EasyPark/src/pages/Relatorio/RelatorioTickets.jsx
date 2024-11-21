import React, { useState, useEffect } from 'react';
import { RelatorioBase } from '../../components/RelatorioBase/RelatorioBase';

export function RelatorioTickets() {
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    const fetchTickets = async () => {
      const data = await fetch('/api/tickets').then(res => res.json());
      setTickets(data);
    };

    fetchTickets();
  }, []);

  const ticketsOrdenados = tickets.sort((a, b) => new Date(b.dataCriacao) - new Date(a.dataCriacao));

  return (
    <RelatorioBase title="Tickets">
      <ul className="list-group">
        {ticketsOrdenados.map((ticket, index) => (
          <li key={index} className="list-group-item">{ticket.descricao} - {new Date(ticket.dataCriacao).toLocaleString()}</li>
        ))}
      </ul>
    </RelatorioBase>
  );
} 