import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button } from "../../components/button/Button";
import './CaixaHome.css';

export function CaixaHome() {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Lógica para logout
    navigate('/');
  };

  return (
    <div className="caixa-home">
      <h1>Bem-vindo ao EasyPark!</h1>
      <div className="menu">
        <Button onClick={() => navigate('/configuracao-estacionamento')}>
          Configuração de Estacionamento (Visualização)
        </Button>
        <Button onClick={() => navigate('/relatorio-usuarios')}>
          Relatório de Usuários
        </Button>
        <Button onClick={() => navigate('/gerenciamento-cliente')}>
          Gerenciamento de Cliente
        </Button>
        <Button onClick={() => navigate('/gerenciamento-plano')}>
          Gerenciamento de Plano
        </Button>
        <Button onClick={() => navigate('/gerenciamento-assinatura')}>
          Gerenciamento de Assinatura
        </Button>
        <Button onClick={() => navigate('/tela-ticket')}>
          Tela do Ticket
        </Button>
      </div>
      <Button className="logout-button" onClick={handleLogout}>
        Logout
      </Button>
    </div>
  );
} 

export default CaixaHome;  