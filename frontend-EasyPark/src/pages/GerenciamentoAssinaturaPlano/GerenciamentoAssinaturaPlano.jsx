import React, { useState, useEffect } from 'react';
import { fetchAssinaturas, atualizarStatusAssinatura } from '../../services/AssinaturaService';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
// import mockData from '../../mock/mockData';
import { useNavigate } from 'react-router-dom';
import { FiClipboard } from 'react-icons/fi';
import './GerenciamentoAssinaturaPlano.css';
import { ToastContainer, toast } from 'react-toastify';

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
  const [filtroStatus, setFiltroStatus] = useState('todos');
  const [assinaturasFiltradas, setAssinaturasFiltradas] = useState([]);

  const carregarAssinaturas = async () => {
    try {
      const data = await fetchAssinaturas();
      setAssinaturas(data);
      aplicarFiltro(data, filtroStatus);
    } catch (error) {
      console.error('Erro ao carregar assinaturas:', error);
    }
  };

  useEffect(() => {
    carregarAssinaturas();
  }, []);

  const aplicarFiltro = (data, status) => {
    if (status === 'todos') {
      setAssinaturasFiltradas(data);
    } else {
      const filtradas = data.filter(assinatura => 
        status === 'ativos' ? assinatura.ativo : !assinatura.ativo
      );
      setAssinaturasFiltradas(filtradas);
    }
  };

  const handleFiltroChange = (e) => {
    const novoStatus = e.target.value;
    setFiltroStatus(novoStatus);
    aplicarFiltro(assinaturas, novoStatus);
  };

  const handleUpdateStatus = async (id, novoStatus) => {
    try {
      await atualizarStatusAssinatura(id, novoStatus);
      await carregarAssinaturas(); // Recarrega os dados após atualização
      toast.success(`Assinatura ${novoStatus ? 'ativada' : 'desativada'} com sucesso!`);
    } catch (error) {
      console.error('Erro ao atualizar status:', error);
      toast.error('Erro ao atualizar status da assinatura');
    }
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
                  value={filtroStatus}
                  onChange={handleFiltroChange}
                  className="filter-input"
                >
                  <option value="todos">Mostrar Todos</option>
                  <option value="ativos">Ativos</option>
                  <option value="inativos">Inativos</option>
                </select>
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
                    <td>{assinatura.usuarioDTO?.email || 'N/A'}</td>
                    <td>{`${assinatura.planoDTO?.tipoPlano} - ${assinatura.planoDTO?.tipoVeiculo}` || 'N/A'}</td>
                    <td>{new Date(assinatura.dataPagamento).toLocaleDateString('pt-BR')}</td>
                    <td>{new Date(assinatura.dataVencimento).toLocaleDateString('pt-BR')}</td>
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
          <ToastContainer position="top-right" autoClose={3000} />
      </div>
    </div>
  );
}