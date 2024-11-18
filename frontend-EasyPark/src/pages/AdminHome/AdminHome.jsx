import React from 'react';
import { useNavigate } from 'react-router-dom';
import { BackgroundWrapper } from '../../components/backgroundWrapper/BackgroundWrapper';
import { Card } from '../../components/card/Card';
import { Button } from '../../components/button/Button';
import './AdminHome.css';

export function AdminHome() {
  const navigate = useNavigate();

  return (
    <BackgroundWrapper>
      <Card title="Admin Home">
        <div className="menu">
          <Button onClick={() => navigate('/configuracao-estacionamento-admin')}>Configuração de Estacionamento</Button>
          <Button onClick={() => navigate('/gerenciamento-cliente')}>Gerenciamento de Cliente</Button>
          <Button onClick={() => navigate('/gerenciamento-plano')}>Gerenciamento de Plano</Button>
          <Button onClick={() => navigate('/gerenciamento-assinatura')}>Gerenciamento de Assinatura</Button>
          <Button onClick={() => navigate('/relatorio')}>Relatório</Button>
          <Button onClick={() => navigate('/configuracao-acesso')}>Configuração de Acesso</Button>
        </div>
      </Card>
    </BackgroundWrapper>
  );
}

export default AdminHome;  