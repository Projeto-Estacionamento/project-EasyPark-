import React, { useState, useEffect } from 'react';

export function RelatorioTickets() {
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    // Fetch tickets do backend
    const fetchTickets = async () => {
      // Simulação de fetch
      const data = await fetch('/api/tickets').then(res => res.json());
      setTickets(data);
    };

    fetchTickets();
  }, []);

  const ticketsOrdenados = tickets.sort((a, b) => new Date(b.dataCriacao) - new Date(a.dataCriacao));

  return (
    <div>
      <h2>Tickets</h2>
      <ul>
        {ticketsOrdenados.map((ticket, index) => (
          <li key={index}>{ticket.descricao} - {new Date(ticket.dataCriacao).toLocaleString()}</li>
        ))}
      </ul>
    </div>
  );
} 