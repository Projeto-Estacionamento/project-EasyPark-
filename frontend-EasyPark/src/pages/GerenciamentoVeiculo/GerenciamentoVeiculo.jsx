import React, { useState, useEffect } from 'react';
import { ListaVeiculos } from './ListaVeiculos';
import { NovoVeiculo } from './NovoVeiculo';
import { fetchVeiculos, criarVeiculo } from '../../services/VeiculoService';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Card } from '../../components/card/Card';
import { Button } from '../../components/button/button';
import './GerenciamentoVeiculo.css';

export function GerenciamentoVeiculo() {
  const [veiculos, setVeiculos] = useState([]);
  const [mostrarNovoVeiculo, setMostrarNovoVeiculo] = useState(false);

  useEffect(() => {
    const carregarVeiculos = async () => {
      const data = await fetchVeiculos();
      setVeiculos(data);
    };

    carregarVeiculos();
  }, []);

  const adicionarVeiculo = async (novoVeiculo) => {
    const veiculoCriado = await criarVeiculo(novoVeiculo);
    setVeiculos([...veiculos, veiculoCriado]);
  };

  return (
    <div className="d-flex home-container">
      <SidebarMenu />
      <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center">
        <Card title="Gerenciamento de Veículos">
          <Button onClick={() => setMostrarNovoVeiculo(!mostrarNovoVeiculo)} variant="outline-light">
            {mostrarNovoVeiculo ? 'Cancelar' : 'Novo Veículo'}
          </Button>
          {mostrarNovoVeiculo && <NovoVeiculo adicionarVeiculo={adicionarVeiculo} />}
          <h2>Lista de Veículos</h2>
          <ListaVeiculos veiculos={veiculos} />
        </Card>
      </div>
    </div>
  );
}
