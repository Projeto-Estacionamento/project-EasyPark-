import React, { useState } from 'react';
import { RelatorioUsuariosClientes } from './RelatorioUsuariosClientes';
import { RelatorioAssinaturas } from './RelatorioAssinaturas';
import { RelatorioTickets } from './RelatorioTickets';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import 'bootstrap/dist/css/bootstrap.min.css';
// import './Relatorio.css';

export function Relatorio() {
  const [activeSection, setActiveSection] = useState('usuarios');

  return (
    <div className="d-flex">
      <SidebarMenu onSelect={setActiveSection} />
      <div className="flex-grow-1">
        <div className="d-flex align-items-center justify-content-between" style={{ backgroundColor: '#00838f', color: 'white', padding: '1rem', borderRadius: '0 0 8px 8px' }}>
          <h1 className="m-0" style={{ fontSize: '2rem' }}>Relatório</h1>
          <div className="d-flex justify-content-around w-50">
            <Button onClick={() => setActiveSection('usuarios')} variant="outline-light">
              Usuários/Clientes
            </Button>
            <Button onClick={() => setActiveSection('assinaturas')} variant="outline-light">
              Assinaturas
            </Button>
            <Button onClick={() => setActiveSection('tickets')} variant="outline-light">
              Tickets
            </Button>
          </div>
        </div>
        <div className="p-3">
          {activeSection === 'usuarios' && <RelatorioUsuariosClientes />}
          {activeSection === 'assinaturas' && <RelatorioAssinaturas />}
          {activeSection === 'tickets' && <RelatorioTickets />}
        </div>
      </div>
    </div>
  );
}