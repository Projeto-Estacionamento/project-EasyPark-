import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import logo from '../../assets/logo.png';

export function SidebarMenu() {
  return (
    <div className="d-flex flex-column vh-100 p-3" style={{ width: '250px', backgroundColor: '#00838f' }}>
      <Link to="/" className="mb-4 text-center">
        <img src={logo} alt="Logo" className="img-fluid mb-3" style={{ maxWidth: '100px' }} />
      </Link>
      <div className="flex-grow-1 d-flex flex-column justify-content-evenly">
        <Link to="/relatorio" className="btn btn-outline-light" style={{ width: '100%' }}>
          Relatório
        </Link>
        <Link to="/configuracao" className="btn btn-outline-light" style={{ width: '100%' }}>
          Configuração
        </Link>
        <Link to="/gerenciamento" className="btn btn-outline-light" style={{ width: '100%' }}>
          Gerenciamento
        </Link>
        <button className="btn btn-outline-danger" style={{ width: '100%' }}>
          Logout
        </button>
      </div>
    </div>
  );
} 