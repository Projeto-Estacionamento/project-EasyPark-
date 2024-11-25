import React, { useState, useEffect } from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import 'bootstrap/dist/css/bootstrap.min.css';
import { listarTickets } from '../../services/TicketService';

export function Relatorio() {
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    const carregarTickets = async () => {
      try {
        const data = await listarTickets();
        setTickets(data);
      } catch (error) {
        console.error('Erro ao carregar tickets:', error);
      }
    };

    carregarTickets();
  }, []);

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="flex-grow-1" style={{ marginLeft: '0px', backgroundColor: '#f8f9fa', overflowY: 'auto', height: '100vh' }}>
        <div className="d-flex align-items-center justify-content-center" style={{ backgroundColor: '#00838f', color: 'white', padding: '1rem', borderRadius: '0 0 8px 8px' }}>
          <h1 className="m-0" style={{ fontSize: '2rem' }}>Relatório Ticket</h1>
        </div>
        <div className="p-3">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Placa do Veículo</th>
                <th>Tipo de Ticket</th>
                <th>Data e Hora de Chegada</th>
              </tr>
            </thead>
            <tbody>
              {tickets.map((ticket) => (
                <tr key={ticket.id}>
                  <td>{ticket.placaVeiculo}</td>
                  <td>{ticket.tipoTicket}</td>
                  <td>{new Date(ticket.horaChegada).toLocaleString()}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}