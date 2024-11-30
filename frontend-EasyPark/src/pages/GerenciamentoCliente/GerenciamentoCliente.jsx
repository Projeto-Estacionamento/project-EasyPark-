import React, { useState, useEffect } from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Card } from '../../components/card/Card';
import { Button } from '../../components/button/button';
import { fetchClientes } from '../../services/ClienteService';
import './GerenciamentoCliente.css';
import mockData from '../../mock/mockData';
import { useNavigate } from 'react-router-dom';
import { FiUserPlus } from 'react-icons/fi';

export function GerenciamentoCliente() {
  const navigate = useNavigate();
  const [clientes, setClientes] = useState([]);
  const [clientesFiltrados, setClientesFiltrados] = useState([]);
  const [filtros, setFiltros] = useState({
    cpf: '',
    email: ''
  });

  const columns = [
    { field: 'nome', header: 'Nome', width: '200px' },
    { field: 'cpf', header: 'CPF', width: '150px' },
    { field: 'email', header: 'Email', width: '200px' },
    { field: 'telefone', header: 'Telefone', width: '150px' },
  ];

  useEffect(() => {
    carregarClientes();
  }, []);

  const carregarClientes = async () => {
    try {
      const data = mockData.usuarios;
      setClientes(data);
      setClientesFiltrados(data);
    } catch (error) {
      console.error('Erro ao carregar clientes:', error);
    }
  };

  const handleFiltroChange = (e) => {
    const { name, value } = e.target;
    setFiltros(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const aplicarFiltros = () => {
    const clientesFiltrados = clientes.filter(cliente => 
      cliente.cpf.includes(filtros.cpf) && cliente.email.includes(filtros.email)
    );
    setClientesFiltrados(clientesFiltrados);
  };

  const limparFiltros = () => {
    setFiltros({ cpf: '', email: '' });
    setClientesFiltrados(clientes);
  };

  const handleNovoCliente = () => {
    navigate('/novo/cliente');
  };

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="gerenciamento-cliente-container">
          <div className="header-container">
            <h2 className="header-title">Gerenciamento de Clientes</h2>
            <Button 
              className="btn-novo-cliente"
              onClick={handleNovoCliente}
            >
              <FiUserPlus style={{ marginRight: '0.5rem' }} size={20} />
              Novo Cliente
            </Button>
          </div>
          
          <div className="table-header">
            <div className="table-actions">
              <div className="search-filters">
                <input
                  type="text"
                  placeholder="Filtrar por CPF"
                  name="cpf"
                  value={filtros.cpf}
                  onChange={handleFiltroChange}
                  className="filter-input"
                />
                <input
                  type="text"
                  placeholder="Filtrar por Email"
                  name="email"
                  value={filtros.email}
                  onChange={handleFiltroChange}
                  className="filter-input"
                />
                <Button onClick={aplicarFiltros} className="filter-button">
                  Aplicar
                </Button>
                <Button onClick={limparFiltros} className="filter-button">
                  Limpar
                </Button>
              </div>
            </div>
          </div>

          <div className="table-container">
            <table className="data-table">
              <thead>
                <tr>
                  {columns.map((col) => (
                    <th key={col.field} style={{ width: col.width }}>
                      {col.header}
                    </th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {clientesFiltrados.map((cliente) => (
                  <tr key={cliente.id}>
                    <td>{cliente.nome}</td>
                    <td>{formatarCPF(cliente.cpf)}</td>
                    <td>{cliente.email}</td>
                    <td>{cliente.telefone}</td>
                    
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
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