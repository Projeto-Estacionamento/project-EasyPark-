import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button } from '../../components/button/Button';
import './RelatorioUsuarios.css';

export function RelatorioUsuarios() {
  const navigate = useNavigate();

  return (
    <div className="relatorio-usuarios">
      <h1>Relatório de Usuários</h1>
      {/* Adicione aqui a lógica e os componentes necessários para exibir o relatório de usuários */}
      <Button className="back-button" onClick={() => navigate('/caixa-home')}>
        Voltar para Caixa Home
      </Button>
    </div>
  );
} 