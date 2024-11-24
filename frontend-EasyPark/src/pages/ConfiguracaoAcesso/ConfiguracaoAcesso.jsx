import React, { useState, useEffect } from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Card } from '../../components/card/Card';
import { Button } from '../../components/button/button';
import ListaUsuarios from './ListaUsuarios';
import { NovoAcesso } from './NovoAcesso';
import './ConfiguracaoAcesso.css';

export function ConfiguracaoAcesso() {
  const [usuarios, setUsuarios] = useState([]);
  const [mostrarNovoAcesso, setMostrarNovoAcesso] = useState(false);

  useEffect(() => {
    fetch('http://localhost:8080/acesso')
      .then(response => response.json())
      .then(data => setUsuarios(data))
      .catch(error => console.error('Erro ao buscar usuários:', error));
  }, []);

  const adicionarUsuario = (novoUsuario) => {
    setUsuarios([...usuarios, novoUsuario]);
  };

  const isAdmin = sessionStorage.getItem('accessType') === 'ADMINISTRADOR';

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="configuracao-acesso-container">
        <Card title="Configuração de Acesso">
          {isAdmin && (
            <Button variant="outline-light" fullWidth onClick={() => setMostrarNovoAcesso(!mostrarNovoAcesso)}>
              {mostrarNovoAcesso ? 'Cancelar' : 'Novo Acesso'}
            </Button>
          )}
          {mostrarNovoAcesso && (
            <NovoAcesso adicionarUsuario={adicionarUsuario} />
          )}
          <ListaUsuarios usuarios={usuarios} />
        </Card>
      </div>
    </div>
  );
}