import React, { useState, useEffect } from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import { Card } from '../../components/card/Card';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Home.css';
import { criarTicket, finalizarTicket, listarTickets } from '../../services/TicketService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import mockData from '../../mock/mockData';

export function Home() {
  const [modoEntrada, setModoEntrada] = useState(false);
  const [modoSaida, setModoSaida] = useState(false);
  const [placaVeiculo, setPlacaVeiculo] = useState('');
  const [tickets, setTickets] = useState([]);
  const [ticketSelecionado, setTicketSelecionado] = useState('');

  const carregarTickets = async () => {
    try {
      const data = mockData.tickets;
      // const data = await listarTickets();
      setTickets(data);
    } catch (error) {
      toast.error('Erro ao carregar tickets.');
    }
  };

  useEffect(() => {
    carregarTickets();
  }, []);

  const handleEntrada = async () => {
    try {
      await criarTicket({ placaVeiculo });
      toast.success('Entrada registrada com sucesso!');
      setPlacaVeiculo('');
      setModoEntrada(false);
      carregarTickets();
    } catch (error) {
      toast.error('Erro ao registrar entrada.');
    }
  };

  const handleSaida = async () => {
    try {
      await finalizarTicket(ticketSelecionado);
      toast.success('Saída registrada com sucesso!');
      setTicketSelecionado('');
      setModoSaida(false);
      carregarTickets();
    } catch (error) {
      toast.error('Erro ao registrar saída.');
    }
  };

  return (
    <div className="d-flex home-container">
      <ToastContainer position="top-right" autoClose={3000} />
      <SidebarMenu />
      <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center">
        <Card>
          {!modoEntrada && !modoSaida ? (
            <div className="d-flex flex-column justify-content-around align-items-center" style={{ height: '300px', width: '400px' }}>
              <Button variant="outline-light" style={{ width: '100%' }} onClick={() => setModoEntrada(true)}>
                Entrada
              </Button>
              <Button variant="outline-light" style={{ width: '100%' }} onClick={() => setModoSaida(true)}>
                Saída
              </Button>
            </div>
          ) : modoEntrada ? (
            <div className="d-flex flex-column justify-content-around align-items-center" style={{ height: '300px', width: '400px' }}>
              <Button variant="outline-light" style={{ width: '100%' }} onClick={() => setModoEntrada(false)}>
                Fechar
              </Button>
              <input
                type="text"
                placeholder="Placa do Veículo"
                value={placaVeiculo}
                onChange={(e) => setPlacaVeiculo(e.target.value)}
                className="form-control mb-3"
                style={{ width: '100%' }}
              />
              <Button variant="outline-light" style={{ width: '100%' }} onClick={handleEntrada}>
                Registrar Entrada
              </Button>
            </div>
          ) : (
            <div className="d-flex flex-column justify-content-around align-items-center" style={{ height: '300px', width: '400px' }}>
              <Button variant="outline-light" style={{ width: '100%' }} onClick={() => setModoSaida(false)}>
                Fechar
              </Button>
              <select
                value={ticketSelecionado}
                onChange={(e) => setTicketSelecionado(e.target.value)}
                className="form-control mb-3"
                style={{ width: '100%' }}
              >
                <option value="">Selecione uma Placa</option>
                {tickets.map((ticket) => (
                  <option key={ticket.id} value={ticket.id}>
                    {ticket.placaVeiculo}
                  </option>
                ))}
              </select>
              <Button variant="outline-light" style={{ width: '100%' }} onClick={handleSaida}>
                Registrar Saída
              </Button>
            </div>
          )}
        </Card>
      </div>
    </div>
  );
}

export default Home; 