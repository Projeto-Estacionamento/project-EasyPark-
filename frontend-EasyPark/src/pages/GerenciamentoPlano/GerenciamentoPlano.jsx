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
      try {
        const data = await fetchPlanos();
        setPlanos(data);
      } catch (error) {
        console.error('Erro ao carregar planos:', error);
      }
    };

    carregarPlanos();
  }, []);

  const adicionarPlano = async (novoPlano) => {
    try {
      const planoCriado = await criarPlano(novoPlano);
      setPlanos([...planos, planoCriado]);
    } catch (error) {
      console.error('Erro ao adicionar plano:', error);
    }
  };

  const planosFiltrados = planos.filter(plano => 
    filtroTipoVeiculo ? plano.tipoVeiculo === filtroTipoVeiculo : true
  );

  return (
    <div className="d-flex home-container">
      <SidebarMenu />
      <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center">
        <Card title="Gerenciamento de Plano">
          <Button onClick={() => setMostrarNovoPlano(!mostrarNovoPlano)} variant={mostrarNovoPlano ? 'outline-light' : 'outline-light'}>
            {mostrarNovoPlano ? 'Cancelar' : 'Novo Plano'}
          </Button>
          {mostrarNovoPlano && <NovoPlano adicionarPlano={adicionarPlano} />}
          <h2 style={{ marginTop: '20px' }}>Lista de Planos</h2>
          <div style={{ marginBottom: '20px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            <select
              onChange={(e) => setFiltroTipoVeiculo(e.target.value)}
              value={filtroTipoVeiculo}
              className="form-control"
              style={{ marginRight: '10px', width: '200px', height: '38px' }}
            >
              <option value="">Todos os Veículos</option>
              <option value="MOTO">Moto</option>
              <option value="CARRO">Carro</option>
            </select>
            <Button
              onClick={() => setFiltroTipoVeiculo('')}
              variant="outline-light"
              style={{ height: '38px' }}
            >
              Limpar
            </Button>
          </div>
          <ListaPlanos planos={planosFiltrados} />
        </Card>
      </div>
    </div>
  );
} 