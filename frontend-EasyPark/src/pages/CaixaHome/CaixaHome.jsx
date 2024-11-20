import React from 'react';
import { useNavigate } from 'react-router-dom';
import { BackgroundWrapper } from '../../components/backgroundWrapper/BackgroundWrapper';
import { Card } from '../../components/card/Card';
import { Button } from '../../components/button/button';
import { PageContainer } from '../../components/pageContainer/PageContainer';

export function CaixaHome() {
  const navigate = useNavigate();

  return (
    <BackgroundWrapper>
      <PageContainer darkMode>
        <Card title="Caixa Home">
          <div className="menu">
            <Button
              style={{ backgroundColor: '#dc3545', color: 'white' }}
              onClick={() => navigate('/')}
            >
              Logout
            </Button>
            <Button onClick={() => navigate('/configuracao-estacionamento-caixa')}>Configuração de Estacionamento (Visualização)</Button>
            <Button onClick={() => navigate('/gerenciamento-cliente')}>Gerenciamento de Cliente</Button>
            <Button onClick={() => navigate('/gerenciamento-plano')}>Gerenciamento de Plano</Button>
            <Button onClick={() => navigate('/gerenciamento-assinatura')}>Gerenciamento de Assinatura</Button>
            <Button onClick={() => navigate('/relatorio')}>Relatório</Button>
          </div>
        </Card>
      </PageContainer>
    </BackgroundWrapper>
  );
}

export default CaixaHome;  