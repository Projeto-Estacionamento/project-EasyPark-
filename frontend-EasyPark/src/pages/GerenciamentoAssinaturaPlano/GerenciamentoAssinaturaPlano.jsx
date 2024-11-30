import React, { useState, useEffect } from 'react';
import { fetchAssinaturas, atualizarStatusAssinatura } from '../../services/AssinaturaService';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
// import mockData from '../../mock/mockData';
import { useNavigate } from 'react-router-dom';
import { FiClipboard } from 'react-icons/fi';
import './GerenciamentoAssinaturaPlano.css';

/* id: 1,
usuario: 1,
plano: 1,
dataPagamento: "2024-03-01T10:00:00",
dataVencimento: "2024-04-01T10:00:00",
ativo: true */

const columns = [
  { field: 'usuario', header: 'Usuário', width: '15%' },
  { field: 'plano', header: 'Plano', width: '15%' },
  { field: 'dataPagamento', header: 'Data de Pagamento', width: '15%' },
  { field: 'dataVencimento', header: 'Data de Vencimento', width: '15%' },
  { field: 'ativo', header: 'Ativo', width: '15%' },
];

export function GerenciamentoAssinaturaPlano() {
  const navigate = useNavigate();
  const [assinaturas, setAssinaturas] = useState([]);
  const [filtroStatus, setFiltroStatus] = useState('');
  const [assinaturasFiltradas, setAssinaturasFiltradas] = useState([]);

  // useEffect(() => {
  //   const carregarAssinaturas = async () => {
  //     // const data = await fetchAssinaturas();
  //     const data = mockData.assinaturas;
  //     setAssinaturas(data);
  //     setAssinaturasFiltradas(data);
  //   };

  //   carregarAssinaturas();
  // }, []);

  // const handleUpdateStatus = async (id, novoStatus) => {
  //   const assinaturaAtualizada = await atualizarStatusAssinatura(id, novoStatus);
  //   setAssinaturas(assinaturas.map(assinatura => 
  //     assinatura.id === id ? assinaturaAtualizada : assinatura
  //   ));
  // };

  useEffect(() => {
    const carregarAssinaturas = async () => {
      try {
        const data = await fetchAssinaturas(); // Busca as assinaturas do banco de dados
        setAssinaturas(data);
        setAssinaturasFiltradas(data);
      } catch (error) {
        console.error('Erro ao carregar assinaturas:', error);
      }
    };

    carregarAssinaturas();
  }, []);

  const handleUpdateStatus = async (id, novoStatus) => {
    try {
      const assinaturaAtualizada = await atualizarStatusAssinatura(id, novoStatus);
      setAssinaturas(assinaturas.map(assinatura => 
        assinatura.id === id ? assinaturaAtualizada : assinatura
      ));
    } catch (error) {
      console.error('Erro ao atualizar status da assinatura:', error);
    }
  };
  
  const handleFiltroChange = (e) => {
    setFiltroStatus(e.target.value);
  };

  const aplicarFiltros = () => {
    const assinaturasFiltradas = assinaturas.filter(assinatura => assinatura.ativo.toString() === filtroStatus || filtroStatus === '');
    setAssinaturasFiltradas(assinaturasFiltradas);
  };

  const limparFiltros = () => {
    setFiltroStatus('');
    setAssinaturasFiltradas(assinaturas);
  };

  const handleNovoAssinatura = () => {
    navigate('/novo/assinatura');
  };

  return (
    <div className="d-flex home-container">
      <SidebarMenu />
      <div className="gerenciamento-cliente-container">
          <div className="header-container">
            <h2 className="header-title">Gerenciamento de Assinaturas</h2>
            <Button 
              className="btn-novo-assinatura"
              onClick={handleNovoAssinatura}
            >
              <FiClipboard style={{ marginRight: '0.5rem' }} size={20} />
              Nova Assinatura
            </Button>
          </div>
          
          <div className="table-header">
            <div className="table-actions">
              <div className="search-filters">
                <select
                  name=""
                  value={filtroStatus}
                  onChange={handleFiltroChange}
                  className="filter-input"
                >
                  <option value="true">Ativo</option>
                  <option value="false">Inativo</option>
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
                {assinaturasFiltradas.map((assinatura) => (
                  <tr key={assinatura.id}>
                      <td>{assinatura.usuario}</td>
                      <td>{assinatura.plano}</td>
                      <td>{assinatura.dataPagamento}</td>
                      <td>{assinatura.dataVencimento}</td>
                      <td>
                        <Button
                          className="btn-status"
                          onClick={() => handleUpdateStatus(assinatura.id, !assinatura.ativo)}
                        >
                          {assinatura.ativo ? 'Desativar' : 'Ativar'}
                        </Button>
                      </td>                
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
      </div>
    </div>
  );
}