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
  const [filtroCpf, setFiltroCpf] = useState('');
  const [filtroEmail, setFiltroEmail] = useState('');

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

  const aplicarFiltro = () => {
    // Lógica para aplicar o filtro
  };

  const limparFiltro = () => {
    setFiltroCpf('');
    setFiltroEmail('');
  };

  const clientesFiltrados = clientes.filter(cliente =>
    cliente.cpf.includes(filtroCpf) && cliente.email.includes(filtroEmail)
  );

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="configuracao-acesso-container">
        <Card title="Gerenciamento de Clientes">
          <Button variant="outline-light" fullWidth onClick={() => setMostrarNovoCliente(!mostrarNovoCliente)}>
            {mostrarNovoCliente ? 'Cancelar' : 'Novo Cliente'}
          </Button>
          {mostrarNovoCliente && <NovoCliente adicionarCliente={adicionarCliente} />}
          <h3 style={{ marginTop: '20px' }}>Lista de Clientes</h3>
          <div style={{ marginBottom: '20px', display: 'flex', alignItems: 'center' }}>
            <input
              type="text"
              placeholder="Filtrar por CPF"
              value={filtroCpf}
              onChange={(e) => setFiltroCpf(e.target.value)}
              style={{ marginRight: '10px' }}
            />
            <input
              type="text"
              placeholder="Filtrar por Email"
              value={filtroEmail}
              onChange={(e) => setFiltroEmail(e.target.value)}
              style={{ marginRight: '10px' }}
            />
            <Button onClick={aplicarFiltro} variant="outline-light" style={{ marginRight: '10px' }}>
              Aplicar
            </Button>
            <Button onClick={limparFiltro} variant="outline-light">
              Limpar
            </Button>
          </div>
          <ListaClientes clientes={clientesFiltrados} />
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