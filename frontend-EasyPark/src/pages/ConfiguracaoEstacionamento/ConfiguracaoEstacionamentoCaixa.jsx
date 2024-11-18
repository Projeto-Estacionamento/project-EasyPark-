import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { PageContainer } from '../../components/pageContainer/PageContainer';
import { Card } from '../../components/card/Card';
import { ConfiguracaoForm } from '../../components/configuracao/ConfiguracaoForm';
import { getConfiguracaoAtual } from '../../services/EstacionamentoService';
import './ConfiguracaoEstacionamento.css';

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
    <PageContainer darkMode>
      <Card title="Configuração de Estacionamento">
        <button className="back-button" onClick={() => navigate('/caixa')}>
          Voltar para Home
        </button>
        <ConfiguracaoForm
          configuracao={configuracao}
          handleChange={() => {}}
          handleSubmit={() => {}}
          isReadOnly={true}
        />
      </Card>
    </PageContainer>
  );
}