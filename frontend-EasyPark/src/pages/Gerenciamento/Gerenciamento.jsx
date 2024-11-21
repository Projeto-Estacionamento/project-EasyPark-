import React from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import { Card } from '../../components/card/Card';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Gerenciamento.css';

export function Gerenciamento() {
  return (
    <div className="d-flex gerenciamento-container">
      <SidebarMenu />
      <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center">
        <Card>
          <h2 className="mb-4" style={{ color: 'white' }}>Gerenciamento</h2>
          <div className="d-flex flex-column justify-content-around align-items-center" style={{ height: '400px', width: '400px' }}>
            <Button variant="outline-light" style={{ width: '100%' }} onClick={() => window.location.href = '/gerenciamento-cliente'}>
              Cliente
            </Button>
            <Button variant="outline-light" style={{ width: '100%' }} onClick={() => window.location.href = '/gerenciamento-plano'}>
              Plano
            </Button>
            <Button variant="outline-light" style={{ width: '100%' }} onClick={() => window.location.href = '/gerenciamento-assinatura-plano'}>
              Assinatura
            </Button>
          </div>
        </Card>
      </div>
    </div>
  );
}

export default Gerenciamento;
