import React, { useState } from 'react';
import { RelatorioUsuariosClientes } from './RelatorioUsuariosClientes';
import { RelatorioAssinaturas } from './RelatorioAssinaturas';
import { RelatorioTickets } from './RelatorioTickets';

export function Relatorio() {
  const [activeSection, setActiveSection] = useState('usuarios');

  return (
    <div className="relatorio">
      <h1>Relatório</h1>
      <div className="menu">
        <button onClick={() => setActiveSection('usuarios')}>Usuários/Clientes</button>
        <button onClick={() => setActiveSection('assinaturas')}>Assinaturas</button>
        <button onClick={() => setActiveSection('tickets')}>Tickets</button>
      </div>
      <div className="content">
        {activeSection === 'usuarios' && <RelatorioUsuariosClientes />}
        {activeSection === 'assinaturas' && <RelatorioAssinaturas />}
        {activeSection === 'tickets' && <RelatorioTickets />}
      </div>
    </div>
  );
}