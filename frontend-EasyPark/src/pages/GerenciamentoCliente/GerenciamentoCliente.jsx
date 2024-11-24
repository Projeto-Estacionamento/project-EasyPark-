import React, { useState, useEffect } from 'react';
import { ListaClientes } from './ListaClientes';
import { NovoCliente } from './NovoCliente';
import { AssociarVeiculo } from './AssociarVeiculo';
import { fetchClientes, criarCliente } from '../../services/ClienteService';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Card } from '../../components/card/Card';
import { Button } from '../../components/button/button';
import './GerenciamentoCliente.css';

export function GerenciamentoCliente() {
  const [clientes, setClientes] = useState([]);
  const [clienteSelecionado, setClienteSelecionado] = useState(null);
  const [mostrarNovoCliente, setMostrarNovoCliente] = useState(false);

  useEffect(() => {
    const carregarClientes = async () => {
      try {
        const data = await fetchClientes();
        setClientes(data);
      } catch (error) {
        console.error('Erro ao carregar clientes:', error);
      }
    };

    carregarClientes();
  }, []);

  const adicionarCliente = async (novoCliente) => {
    try {
      const clienteCriado = await criarCliente(novoCliente);
      setClientes([...clientes, clienteCriado]);
    } catch (error) {
      console.error('Erro ao adicionar cliente:', error);
    }
  };

  return (
    <div className="d-flex home-container">
      <SidebarMenu />
      <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center">
        <Card title="Gerenciamento de Cliente">
          <Button onClick={() => setMostrarNovoCliente(!mostrarNovoCliente)} variant={mostrarNovoCliente ? 'outline-danger' : 'outline-light'}>
            {mostrarNovoCliente ? 'Cancelar' : 'Novo Cliente'}
          </Button>
          {mostrarNovoCliente && <NovoCliente adicionarCliente={adicionarCliente} />}
          <ListaClientes clientes={clientes} setClienteSelecionado={setClienteSelecionado} />
          {clienteSelecionado && <AssociarVeiculo cliente={clienteSelecionado} />}
        </Card>
      </div>
    </div>
  );
}