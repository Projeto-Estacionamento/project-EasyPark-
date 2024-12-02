import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import { FiUsers, FiEdit } from "react-icons/fi";
import { listarAcessos, listarAcessosPorTipo } from '../../services/AcessoService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './ConfiguracaoAcesso.css';

const columns = [
  { field: "tipoAcesso", header: "Tipo de Acesso", width: "150px" },
  { field: "email", header: "Email", width: "200px" },
  { field: "actions", header: "Ações", width: "100px" }
];

export function ConfiguracaoAcesso() {
  const [acessos, setAcessos] = useState([]);
  const [acessosFiltrados, setAcessosFiltrados] = useState([]);
  const [filtroTipoAcesso, setFiltroTipoAcesso] = useState("");
  const navigate = useNavigate();
  const isAdmin = sessionStorage.getItem('accessType') === 'ADMINISTRADOR';
  
  const carregarUsuarios = async () => {
    try {
      const data = await listarAcessos();
      setAcessos(data);
      setAcessosFiltrados(data);
    } catch (error) {
      console.error('Erro ao carregar usuários:', error);
      toast.error('Erro ao carregar usuários: ' + (error.response?.data?.message || 'Erro desconhecido'));
    }
  };

  useEffect(() => {
    carregarUsuarios();
  }, []);

  const handleFiltroChange = (e) => {
    setFiltroTipoAcesso(e.target.value);
  };

  const aplicarFiltros = async () => {
    try {
      if (filtroTipoAcesso) {
        const data = await listarAcessosPorTipo(filtroTipoAcesso);
        setAcessosFiltrados(data);
      } else {
        setAcessosFiltrados(acessos);
      }
    } catch (error) {
      console.error('Erro ao aplicar filtros:', error);
      toast.error('Erro ao aplicar filtros');
    }
  };

  const limparFiltros = () => {
    setFiltroTipoAcesso('');
    setAcessosFiltrados(acessos);
  };

  const handleEditarAcesso = (id) => {
    navigate(`/editar-acesso/${id}`);
  };

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="configuracao-acesso-container">
        <div className="header-container">
          <h2 className="header-title">Gerenciamento de Acessos</h2>
          {isAdmin && (
            <Button 
              className="btn-novo-acesso"
              onClick={() => navigate('/novo/acesso')}
            >
              <FiUsers style={{ marginRight: '0.5rem' }} size={20} />
              Novo Acesso
            </Button>
          )}
        </div>
        <div className="table-header">
          <div className="table-actions">
            <div className="search-filters">
              <select
                name="tipoAcesso"
                value={filtroTipoAcesso}
                onChange={handleFiltroChange}
                className="filter-input"
              >
                <option value="">Tipo de Acesso</option>
                <option value="ADMINISTRADOR">Administrador</option>
                <option value="CAIXA">Caixa</option>
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
              {acessosFiltrados.map((usuario) => (
                <tr key={usuario.id}>
                  <td>{usuario.tipoAcesso}</td>
                  <td>{usuario.email}</td>
                  <td>
                    {isAdmin && (
                      <Button 
                        className="btn-editar"
                        onClick={() => handleEditarAcesso(usuario.id)}
                      >
                        <FiEdit size={18} />
                      </Button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <ToastContainer />
      </div>
    </div>
  );
}