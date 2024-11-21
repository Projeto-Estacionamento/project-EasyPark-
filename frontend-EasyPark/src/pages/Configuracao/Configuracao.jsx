import React from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import { Card } from '../../components/card/Card';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Configuracao.css';

export function Configuracao() {
  return (
    <div className="d-flex configuracao-container">
      <SidebarMenu />
      <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center">
        <Card>
          <div className="d-flex flex-column justify-content-around align-items-center" style={{ height: '300px', width: '400px' }}>
            <Button variant="outline-light" style={{ width: '100%' }} onClick={() => window.location.href = '/configuracao-acesso'}>
              Acesso
            </Button>
            <Button variant="outline-light" style={{ width: '100%' }} onClick={() => window.location.href = '/configuracao-estacionamento'}>
              Estacionamento
            </Button>
          </div>
        </Card>
      </div>
    </div>
  );
}

export default Configuracao;
