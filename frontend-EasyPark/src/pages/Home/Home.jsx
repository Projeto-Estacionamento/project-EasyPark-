import React from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import { Card } from '../../components/card/Card';
import 'bootstrap/dist/css/bootstrap.min.css';
import './Home.css';

export function Home() {
  return (
    <div className="d-flex home-container">
      <SidebarMenu />
      <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center">
        <Card>
          <div className="d-flex flex-column justify-content-around align-items-center" style={{ height: '300px', width: '400px' }}>
            <Button variant="outline-light" style={{ width: '100%' }}>
              Entrada
            </Button>
            <Button variant="outline-light" style={{ width: '100%' }}>
              Saída
            </Button>
          </div>
        </Card>
      </div>
    </div>
  );
}

export default Home; 