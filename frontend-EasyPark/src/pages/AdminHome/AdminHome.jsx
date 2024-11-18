import React from 'react';
import { useNavigate } from 'react-router-dom';
import { PageContainer } from '../../components/pageContainer/PageContainer';
import { Card } from '../../components/card/Card';
import { Button } from '../../components/button/Button';
import './AdminHome.css';

export function AdminHome() {
  const navigate = useNavigate();

  return (
    <PageContainer darkMode>
      <Card title="Admin Home">
        <div className="menu">
          <Button onClick={() => navigate('/configuracao-estacionamento')}>Configuração de Estacionamento</Button>
          <Button onClick={() => navigate('/gerenciamento-cliente')}>Gerenciamento de Cliente</Button>
          <Button onClick={() => navigate('/gerenciamento-plano')}>Gerenciamento de Plano</Button>
          <Button onClick={() => navigate('/gerenciamento-assinatura')}>Gerenciamento de Assinatura</Button>
          <Button onClick={() => navigate('/relatorio')}>Relatório</Button>
          <Button onClick={() => navigate('/configuracao-acesso')}>Configuração de Acesso</Button>
        </div>
      </Card>
    </PageContainer>
  );
}
export default AdminHome;  