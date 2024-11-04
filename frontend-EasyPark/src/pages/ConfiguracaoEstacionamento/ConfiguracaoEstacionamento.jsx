import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { ConfiguracaoForm } from '../../components/configuracao/ConfiguracaoForm';
import './ConfiguracaoEstacionamento.css';

export function ConfiguracaoEstacionamento() {
  const [configuracao, setConfiguracao] = useState({
    qtdMoto: 0,
    qtdCarro: 0,
    valorHoraMoto: 0.0,
    valorHoraCarro: 0.0,
    valorDiariaCarro: 0.0,
    valorDiariaMoto: 0.0,
    horaMaximaAvulso: 0.0,
  });

  const navigate = useNavigate();
  const apiUrl = import.meta.env.VITE_API_URL;

  useEffect(() => {
    axios.get(`${apiUrl}/api/configuracoes/atual`)
      .then(response => setConfiguracao(response.data))
      .catch(error => console.error('Erro ao buscar configuração:', error));
  }, [apiUrl]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setConfiguracao({ ...configuracao, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.put(`${apiUrl}/api/configuracoes/${configuracao.id}`, configuracao)
      .then(() => {
        alert('Configuração atualizada com sucesso!');
        navigate('/admin');
      })
      .catch(error => console.error('Erro ao atualizar configuração:', error));
  };

  return (
    <div className="configuracao-estacionamento">
      <h1>Configuração de Estacionamento</h1>
      <ConfiguracaoForm
        configuracao={configuracao}
        handleChange={handleChange}
        handleSubmit={handleSubmit}
      />
    </div>
  );
}