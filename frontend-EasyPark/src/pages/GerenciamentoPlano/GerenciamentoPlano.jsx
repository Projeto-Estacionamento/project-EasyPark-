import React, { useState, useEffect } from 'react';
import { fetchPlanos } from '../../services/PlanoService';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import { FiPackage } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
import './GerenciamentoPlano.css';
import mockData from '../../mock/mockData';

const columns = [
  { field: "tipoPlano", header: "Tipo de Plano", width: "150px" },
  { field: "tipoVeiculo", header: "Tipo de Veículo", width: "150px" },
  { field: "valorPlano", header: "Valor do Plano", width: "150px" },
  { field: "horarioInicio", header: "Horário Início", width: "150px" },
  { field: "horarioFim", header: "Horário Fim", width: "150px" },
];

export function GerenciamentoPlano() {
  const navigate = useNavigate();
  const [planos, setPlanos] = useState([]);
  const [planosFiltrados, setPlanosFiltrados] = useState([]);
  const [filtroTipoVeiculo, setFiltroTipoVeiculo] = useState("");

  const handleFiltroChange = (e) => {
    setFiltroTipoVeiculo(e.target.value);
  };

  useEffect(() => {
    const carregarPlanos = async () => {
      try {
        const data = mockData.planos;
        // const data = await fetchPlanos(); descomentar quando a API estiver pronta
        setPlanos(data);
        setPlanosFiltrados(data);
      } catch (error) {
        console.error('Erro ao carregar planos:', error);
      }
    };

    carregarPlanos();
  }, []);

  const handleNovoPlano = () => {
    navigate('/novo/plano');
  }

  const aplicarFiltros = () => {
    const planosFiltrados = planos.filter(plano => plano.tipoVeiculo === filtroTipoVeiculo || filtroTipoVeiculo === '');
    setPlanosFiltrados(planosFiltrados);
  };

  const limparFiltros = () => {
    setFiltroTipoVeiculo('');
    setPlanosFiltrados(planos);
  };

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="gerenciamento-cliente-container">
          <div className="header-container">
            <h2 className="header-title">Gerenciamento de Planos</h2>
            <Button 
              className="btn-novo-plano"
              onClick={handleNovoPlano}
            >
              <FiPackage style={{ marginRight: '0.5rem' }} size={20} />
              Novo Plano
            </Button>
          </div>
          
          <div className="table-header">
            <div className="table-actions">
              <div className="search-filters">
                <select
                  name="tipoVeiculo"
                  value={filtroTipoVeiculo}
                  onChange={handleFiltroChange}
                  className="filter-input"
                >
                  <option value="">Tipo de Veículo</option>
                  <option value="CARRO">Carro</option>
                  <option value="MOTO">Moto</option>
                </select>
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
                {planosFiltrados.map((plano) => (
                  <tr key={plano.id}>
                      <td>{plano.tipoPlano}</td>
                      <td>{plano.tipoVeiculo}</td>
                      <td>{plano.valorPlano}</td>
                      <td>{plano.horarioInicio}</td>
                      <td>{plano.horarioFim}</td>
                    
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
      </div>
    </div>
  );
} 