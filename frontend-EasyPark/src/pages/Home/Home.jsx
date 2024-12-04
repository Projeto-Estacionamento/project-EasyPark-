import React, { useState, useEffect } from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import { Card } from '../../components/card/Card';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Home.css';
import { criarTicket, finalizarTicket, listarTickets } from '../../services/TicketService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export function Home() {
  const [modoEntrada, setModoEntrada] = useState(false);
  const [modoSaida, setModoSaida] = useState(false);
  const [placaVeiculo, setPlacaVeiculo] = useState('');
  const [tipoVeiculo, setTipoVeiculo] = useState('');
  const [tickets, setTickets] = useState([]);
  const [ticketSelecionado, setTicketSelecionado] = useState('');
  const [ticketInfo, setTicketInfo] = useState(null);
  const [ticketFinalizadoInfo, setTicketFinalizadoInfo] = useState(null);
  const [modoVisualizacao, setModoVisualizacao] = useState(false);

  const carregarTickets = async () => {
    try {
      const data = await listarTickets();
      setTickets(data);
    } catch (error) {
      toast.error('Erro ao carregar tickets.');
    }
  };

  useEffect(() => {
    carregarTickets();
  }, []);

  const handleEntrada = async () => {
    if (!placaVeiculo || tipoVeiculo === '') {
      toast.error('Por favor, preencha todos os campos corretamente.');
      return;
    }

    try {
      const novoTicket = await criarTicket({ placaVeiculo, tipoVeiculo });
      setTicketInfo(novoTicket);
      setModoVisualizacao(true);
      toast.success('Entrada registrada com sucesso!');
      setPlacaVeiculo('');
      setTipoVeiculo('');
      setModoEntrada(false);
      carregarTickets();
    } catch (error) {
      toast.error('Erro ao registrar entrada.');
    }
  };

  const handleSaida = async () => {
    try {
      const ticketFinalizado = await finalizarTicket(ticketSelecionado);
      setTicketFinalizadoInfo(ticketFinalizado);
      setModoVisualizacao(true);
      toast.success('Saída registrada com sucesso!');
      setTicketSelecionado('');
      setModoSaida(false);
      carregarTickets();
    } catch (error) {
      toast.error('Erro ao registrar saída.');
    }
  };

  const fecharCard = () => {
    setModoVisualizacao(false);
    setTicketInfo(null);
    setTicketFinalizadoInfo(null);
  };

  return (
    <div className="d-flex home-container">
      <ToastContainer position="top-right" autoClose={3000} />
      <SidebarMenu />
      <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center">
        {modoVisualizacao ? (
          <Card className="ticket-info-card">
            <h5 className="ticket-title">{ticketInfo ? 'Informações do Ticket Criado' : 'Informações do Ticket Finalizado'}</h5>
            {ticketInfo && (
              <>
                <p>Placa: {ticketInfo.placaVeiculo}</p>
                <p>Tipo: {ticketInfo.tipoVeiculo}</p>
                <p>Hora de Entrada: {new Date(ticketInfo.horaChegada).toLocaleString()}</p>
              </>
            )}
            {ticketFinalizadoInfo && (
              <>
                <p>Placa: {ticketFinalizadoInfo.placaVeiculo}</p>
                <p>Tipo: {ticketFinalizadoInfo.tipoVeiculo}</p>
                <p>Hora de Entrada: {new Date(ticketFinalizadoInfo.horaChegada).toLocaleString()}</p>
                <p>Hora de Saída: {new Date(ticketFinalizadoInfo.horaSaida).toLocaleString()}</p>
                <p>Valor Total: R$ {ticketFinalizadoInfo.valorTotalPagar}</p>
              </>
            )}
            <Button variant="outline-light" className="close-button" onClick={fecharCard}>
              Fechar
            </Button>
          </Card>
        ) : (
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
                <select
                  value={tipoVeiculo}
                  onChange={(e) => setTipoVeiculo(e.target.value)}
                  className="form-control mb-3 input-custom"
                >
                  <option value="" disabled>Selecione o tipo de veículo</option>
                  <option value="CARRO">Carro</option>
                  <option value="MOTO">Moto</option>
                </select>
                <input
                  type="text"
                  placeholder="Placa do Veículo"
                  value={placaVeiculo}
                  onChange={(e) => setPlacaVeiculo(e.target.value)}
                  className="form-control mb-3 input-custom"
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
        )}
      </div>
    </div>
  );
}

export default Home; 