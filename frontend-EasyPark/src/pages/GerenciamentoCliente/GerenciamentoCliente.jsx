import React, { useState, useEffect } from 'react';
import { ListaClientes } from './ListaClientes';
import { NovoCliente } from './NovoCliente';
import { AssociarVeiculo } from './AssociarVeiculo';
import { fetchClientes, criarCliente } from '../../services/ClienteService';

export function GerenciamentoCliente() {
  const [clientes, setClientes] = useState([]);
  const [clienteSelecionado, setClienteSelecionado] = useState(null);
  const [mostrarNovoCliente, setMostrarNovoCliente] = useState(false);

  useEffect(() => {
    const carregarClientes = async () => {
      const data = await fetchClientes();
      setClientes(data);
    };

    carregarClientes();
  }, []);

  const adicionarCliente = async (novoCliente) => {
    const clienteCriado = await criarCliente(novoCliente);
    setClientes([...clientes, clienteCriado]);
  };

  return (
    <div className="gerenciamento-cliente">
      <h1>Gerenciamento de Cliente</h1>
      <button onClick={() => setMostrarNovoCliente(!mostrarNovoCliente)}>
        {mostrarNovoCliente ? 'Cancelar' : 'Criar Novo Cliente'}
      </button>
      {mostrarNovoCliente && <NovoCliente adicionarCliente={adicionarCliente} />}
      <ListaClientes clientes={clientes} setClienteSelecionado={setClienteSelecionado} />
      {clienteSelecionado && <AssociarVeiculo cliente={clienteSelecionado} />}
    </div>
  );
} 