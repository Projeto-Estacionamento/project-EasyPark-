import React, { useState, useEffect } from 'react';
import { ListaPlanos } from './ListaPlanos';
import { NovoPlano } from './NovoPlano';
import { fetchPlanos, criarPlano } from '../../services/PlanoService';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Card } from '../../components/card/Card';
import { Button } from '../../components/button/button';
import './GerenciamentoPlano.css';

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
    <div className="d-flex home-container">
      <SidebarMenu />
      <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center">
        <Card title="Gerenciamento de Plano">
          <Button onClick={() => setMostrarNovoPlano(!mostrarNovoPlano)} variant={mostrarNovoPlano ? 'outline-danger' : 'outline-light'}>
            {mostrarNovoPlano ? 'Cancelar' : 'Novo Plano'}
          </Button>
          {mostrarNovoPlano && <NovoPlano adicionarPlano={adicionarPlano} />}
          <h2>Lista de Planos</h2>
          <select onChange={(e) => setFiltroTipoVeiculo(e.target.value)} value={filtroTipoVeiculo} className="form-control mb-3">
            <option value="">Todos os Veículos</option>
            <option value="MOTO">Moto</option>
            <option value="CARRO">Carro</option>
          </select>
          <ListaPlanos planos={planosFiltrados} />
        </Card>
      </div>
    </div>
  );
} 