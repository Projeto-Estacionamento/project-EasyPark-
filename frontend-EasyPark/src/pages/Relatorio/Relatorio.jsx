import React, { useState, useEffect } from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import 'bootstrap/dist/css/bootstrap.min.css';
import { listarTickets } from '../../services/TicketService';
import { listarTicketsFechados } from '../../services/RelatorioService';
import { Button } from '../../components/button/button';

export function Relatorio() {
  const [ticketsAbertos, setTicketsAbertos] = useState([]);
  const [ticketsFechados, setTicketsFechados] = useState([]);
  const [mostrarFechados, setMostrarFechados] = useState(false);

  useEffect(() => {
    const carregarTickets = async () => {
      try {
        if (mostrarFechados) {
          const dataFechados = await listarTicketsFechados();
          setTicketsFechados(dataFechados);
        } else {
          const dataAbertos = await listarTickets();
          setTicketsAbertos(dataAbertos);
        }
      } catch (error) {
        console.error('Erro ao carregar tickets:', error);
      }
    };

    carregarTickets();
  }, [mostrarFechados]);

  const horarioChegadaAberto = (ticket) => ticket.horaChegada ? new Date(ticket.horaChegada).toLocaleString() : 'N/A';
  const horarioChegadaFechado = (ticket) => ticket.horaEntrada ? new Date(ticket.horaEntrada).toLocaleString() : 'N/A';

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="flex-grow-1" style={{ marginLeft: '0px', backgroundColor: '#f8f9fa', overflowY: 'auto', height: '100vh' }}>
        <div className="d-flex align-items-center justify-content-center" style={{ backgroundColor: '#00838f', color: 'white', padding: '1rem', borderRadius: '0 0 8px 8px' }}>
          <h1 className="m-0" style={{ fontSize: '2rem' }}>Relatório Ticket</h1>
        </div>
        <div className="p-3">
          <Button onClick={() => setMostrarFechados(!mostrarFechados)} variant="outline-primary">
            {mostrarFechados ? 'Mostrar Abertos' : 'Mostrar Fechados'}
          </Button>
          <table className="table table-striped mt-3">
            <thead>
              <tr>
                <th>Placa do Veículo</th>
                <th>Tipo de Veículo</th>
                <th>Data e Hora de Chegada</th>
                {mostrarFechados && <th>Data e Hora de Saída</th>}
                {mostrarFechados && <th>Valor Total a Pagar</th>}
              </tr>
            </thead>
            <tbody>
              {(mostrarFechados ? ticketsFechados : ticketsAbertos).map((ticket) => (
                <tr key={ticket.id}>
                  <td>{ticket.placaVeiculo || 'N/A'}</td>
                  <td>{ticket.tipoVeiculo || 'N/A'}</td>
                  <td>{mostrarFechados ? horarioChegadaFechado(ticket) : horarioChegadaAberto(ticket)}</td>
                  {mostrarFechados && <td>{ticket.horaSaida ? new Date(ticket.horaSaida).toLocaleString() : 'N/A'}</td>}
                  {mostrarFechados && <td>{ticket.valorTotalPagar.toFixed(2)}</td>}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}