import React, { useState, useEffect } from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Card } from '../../components/card/Card';
import { Button } from '../../components/button/button';
import { ListaClientes } from './ListaClientes';
import { NovoCliente } from './NovoCliente';
import { fetchClientes, criarCliente } from '../../services/ClienteService';
import './GerenciamentoCliente.css';

export function GerenciamentoCliente() {
  const [clientes, setClientes] = useState([]);
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
    <div className="d-flex">
      <SidebarMenu />
      <div className="configuracao-acesso-container">
        <Card title="Gerenciamento de Clientes">
          <Button variant="outline-light" fullWidth onClick={() => setMostrarNovoCliente(!mostrarNovoCliente)}>
            {mostrarNovoCliente ? 'Cancelar' : 'Novo Cliente'}
          </Button>
          {mostrarNovoCliente && <NovoCliente adicionarCliente={adicionarCliente} />}
          <ListaClientes clientes={clientes} />
        </Card>
      </div>
    </div>
  );
}

function formatarCPF(cpf) {
  return cpf
    .replace(/\D/g, '') // Remove caracteres não numéricos
    .replace(/(\d{3})(\d)/, '$1.$2') // Adiciona o primeiro ponto
    .replace(/(\d{3})(\d)/, '$1.$2') // Adiciona o segundo ponto
    .replace(/(\d{3})(\d{1,2})$/, '$1-$2'); // Adiciona o traço
}