import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import { FiUsers } from "react-icons/fi";
import './ConfiguracaoAcesso.css';
// import mockData from '../../mock/mockData';

const columns = [
  { field: "tipoAcesso", header: "Tipo de Acesso", width: "150px" },
  { field: "email", header: "Email", width: "200px" },
];

export function ConfiguracaoAcesso() {
  const [mostrarNovoAcesso, setMostrarNovoAcesso] = useState(false);
  const [acessos, setAcessos] = useState([]);
  const [acessosFiltrados, setAcessosFiltrados] = useState([]);
  const [filtroTipoAcesso, setFiltroTipoAcesso] = useState("");
  const navigate = useNavigate();
  
  const carregarUsuarios = () => {
    // setAcessos(mockData.acessos);
    // setAcessosFiltrados(mockData.acessos);
    fetch('http://localhost:8080/acesso')
      .then(response => response.json())
      .then(data => {
        setAcessos(data);
        setAcessosFiltrados(data);
      })
      .catch(error => console.error('Erro ao buscar usuários:', error));
  };

  useEffect(() => {
    carregarUsuarios();
  }, []);


  const handleFiltroChange = (e) => {
    setFiltroTipoAcesso(e.target.value);
  };

  const aplicarFiltros = () => {
    const filtrados = acessos.filter(acesso => 
      acesso.tipoAcesso === filtroTipoAcesso || filtroTipoAcesso === ''
    );
    setAcessosFiltrados(filtrados);
  };

  const limparFiltros = () => {
    setFiltroTipoAcesso('');
    setAcessos(acessos);
    setAcessosFiltrados(acessos);
  };

  const isAdmin = sessionStorage.getItem('accessType') === 'ADMINISTRADOR';

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
              {mostrarNovoAcesso ? 'Cancelar' : 'Novo Acesso'}
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
                  {columns.map((col) => (
                    <td key={col.field}>{usuario[col.field]}</td>
                  ))}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}