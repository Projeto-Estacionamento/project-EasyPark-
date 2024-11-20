import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button } from '../../components/button/button';

export function EntradaEstacionamento() {
  const navigate = useNavigate();

  return (
    <div className="entrada-estacionamento">
      <h1>Entrada no Estacionamento</h1>
      {/* Adicione aqui a lógica e os componentes necessários para a entrada no estacionamento */}
      <Button className="back-button" onClick={() => navigate('/caixa-home')}>
        Voltar para Caixa Home
      </Button>
    </div>
  );
}