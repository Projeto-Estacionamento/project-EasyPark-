import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { PageContainer } from '../../components/pageContainer/PageContainer';
import { BackgroundWrapper } from '../../components/backgroundWrapper/BackgroundWrapper';
import { Card } from '../../components/card/Card';
import { ConfiguracaoForm } from '../../components/configuracao/ConfiguracaoForm';
import { getConfiguracaoAtual } from '../../services/EstacionamentoService';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu'; // Adicione esta linha

export function ConfiguracaoEstacionamentoCaixa() {
  const [configuracao, setConfiguracao] = useState({
    id: 1,
    qtdMoto: 0,
    qtdCarro: 0,
    valorHoraMoto: 0.0,
    valorHoraCarro: 0.0,
    valorDiariaCarro: 0.0,
    valorDiariaMoto: 0.0,
    horaMaximaAvulso: 0.0,
  });

  const navigate = useNavigate();

  useEffect(() => {
    const fetchConfiguracao = async () => {
      try {
        const data = await getConfiguracaoAtual();
        setConfiguracao(data);
      } catch (error) {
        console.error('Erro ao buscar configuração:', error);
      }
    };

    fetchConfiguracao();
  }, []);

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="configuracao-container">
        <Card title="Configuração de Estacionamento" className="card-config-estacionamento">
          <ConfiguracaoForm
            configuracao={configuracao}
            handleChange={handleChange}
            handleSubmit={handleSubmit}
            isReadOnly={false}
          />
        </Card>
      </div>
    </div>
  );
}