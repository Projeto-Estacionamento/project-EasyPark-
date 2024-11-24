import React, { useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import logo from '../../assets/logo.png';
import { AuthContext } from '../../context/AuthContext';

export function SidebarMenu() {
  const { logout } = useContext(AuthContext);
  const navigate = useNavigate();
  const tipoUsuario = sessionStorage.getItem('accessType');

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  const handleConfiguracaoClick = () => {
    if (tipoUsuario === 'CAIXA') {
      navigate('/configuracao-estacionamento');
    } else {
      navigate('/configuracao');
    }
  };

  return (
    <div className="d-flex flex-column vh-100 p-3" style={{ width: '250px', backgroundColor: '#00838f', borderRight: '2px solid white' }}>
      <Link to="/home" className="mb-4 text-center">
        <img src={logo} alt="Logo" className="img-fluid mb-3" style={{ maxWidth: '100px' }} />
      </Link>
      <div className="flex-grow-1 d-flex flex-column justify-content-evenly">
        <Link to="/relatorio" className="btn btn-outline-light" style={{ width: '100%' }}>
          Relatório
        </Link>
        <button onClick={handleConfiguracaoClick} className="btn btn-outline-light" style={{ width: '100%' }}>
          Configuração
        </button>
        <Link to="/gerenciamento" className="btn btn-outline-light" style={{ width: '100%' }}>
          Gerenciamento
        </Link>
        <button onClick={handleLogout} className="btn btn-outline-danger" style={{ width: '100%' }}>
          Logout
        </button>
      </div>
    </div>
  );
} 