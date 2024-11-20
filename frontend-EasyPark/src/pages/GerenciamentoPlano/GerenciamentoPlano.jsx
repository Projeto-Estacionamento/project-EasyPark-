import React, { useState, useEffect } from 'react';
import { ListaPlanos } from './ListaPlanos';
import { NovoPlano } from './NovoPlano';
import { fetchPlanos, criarPlano } from '../../services/PlanoService';

export function GerenciamentoPlano() {
  const [planos, setPlanos] = useState([]);
  const [mostrarNovoPlano, setMostrarNovoPlano] = useState(false);
  const [filtroTipoVeiculo, setFiltroTipoVeiculo] = useState('');

  useEffect(() => {
    const carregarPlanos = async () => {
      const data = await fetchPlanos();
      setPlanos(data);
    };

    carregarPlanos();
  }, []);

  const adicionarPlano = async (novoPlano) => {
    const planoCriado = await criarPlano(novoPlano);
    setPlanos([...planos, planoCriado]);
  };

  const planosFiltrados = planos.filter(plano => 
    filtroTipoVeiculo ? plano.tipoVeiculo === filtroTipoVeiculo : true
  );

  return (
    <div className="gerenciamento-plano">
      <h1>Gerenciamento de Plano</h1>
      <select onChange={(e) => setFiltroTipoVeiculo(e.target.value)} value={filtroTipoVeiculo}>
        <option value="">Todos os Veículos</option>
        <option value="MOTO">Moto</option>
        <option value="CARRO">Carro</option>
      </select>
      <button onClick={() => setMostrarNovoPlano(!mostrarNovoPlano)}>
        {mostrarNovoPlano ? 'Cancelar' : 'Criar Novo Plano'}
      </button>
      {mostrarNovoPlano && <NovoPlano adicionarPlano={adicionarPlano} />}
      <ListaPlanos planos={planosFiltrados} />
    </div>
  );
} 