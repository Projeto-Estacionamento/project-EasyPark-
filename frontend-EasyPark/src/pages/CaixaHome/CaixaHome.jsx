import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button } from "../../components/button/Button";
import './CaixaPage.css';

export function CaixaPage() {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Lógica para logout
    navigate('/');
  };

  return (
    <div className="home-page">
      <h1>Bem-vindo ao EasyPark!</h1>
      <div className="menu">
        <Button onClick={() => navigate('/entrada-estacionamento')}>
          Entrada no Estacionamento
        </Button>
        <Button onClick={() => navigate('/relatorio-usuarios')}>
          Relatório de Usuários
        </Button>
      </div>
      <Button className="logout-button" onClick={handleLogout}>
        Logout
      </Button>
    </div>
  );
} 