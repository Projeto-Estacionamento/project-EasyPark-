import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button } from '../../components/button/button';
import './ConfiguracaoEstacionamento.css';

export function ConfiguracaoEstacionamento() {
  const navigate = useNavigate();

  return (
    <div className="configuracao-estacionamento">
      <h1>Configuração de Estacionamento</h1>
      <Button onClick={() => navigate('/estacionamento-detalhes')}>
        Ver Detalhes do Estacionamento
      </Button>
    </div>
  );
}