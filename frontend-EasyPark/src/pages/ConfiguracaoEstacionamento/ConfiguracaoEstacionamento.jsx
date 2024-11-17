import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { ConfiguracaoForm } from '../../components/configuracao/ConfiguracaoForm';
import { getConfiguracaoAtual, updateConfiguracao } from '../../services/EstacionamentoService';
import './ConfiguracaoEstacionamento.css';

export function ConfiguracaoEstacionamento({ isAdmin }) {
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

  const handleChange = (e) => {
    const { name, value } = e.target;
    setConfiguracao({ ...configuracao, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (isAdmin) {
      try {
        await updateConfiguracao(configuracao);
        alert('Configuração atualizada com sucesso!');
        navigate('/admin');
      } catch (error) {
        console.error('Erro ao atualizar configuração:', error);
      }
    }
  };

  return (
    <div className="configuracao-estacionamento">
      <h1>Configuração de Estacionamento</h1>
      <ConfiguracaoForm
        configuracao={configuracao}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
        isReadOnly={!isAdmin}
      />
    </div>
  );
}