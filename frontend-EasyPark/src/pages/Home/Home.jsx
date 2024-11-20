import React from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import 'bootstrap/dist/css/bootstrap.min.css';

export function Home() {
  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center p-3">
        <Button className="mb-3" style={{ width: '200px' }}>
          Entrada Estacionamento
        </Button>
        <Button style={{ width: '200px' }}>
          Saída Estacionamento
        </Button>
      </div>
    </div>
  );
}

export default Home; 