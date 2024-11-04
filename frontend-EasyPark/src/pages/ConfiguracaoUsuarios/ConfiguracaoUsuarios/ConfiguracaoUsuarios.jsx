import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button } from '../../components/button/Button';
import './ConfiguracaoUsuarios.css';

export function ConfiguracaoUsuarios() {
  const navigate = useNavigate();

  return (
    <div className="configuracao-usuarios">
      <h1>Configuração de Usuários</h1>
      <Button onClick={() => navigate('/usuarios-detalhes')}>
        Ver Detalhes dos Usuários
      </Button>
    </div>
  );
}