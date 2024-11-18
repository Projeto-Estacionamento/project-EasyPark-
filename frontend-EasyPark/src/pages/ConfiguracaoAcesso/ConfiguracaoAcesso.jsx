import React, { useState, useEffect } from 'react';
import './ConfiguracaoAcesso.css';
import { ListaUsuarios } from './ListaUsuarios';
import { NovoAcesso } from './NovoAcesso';
import { Card } from '../../components/card/Card';

export function ConfiguracaoAcesso() {
  const [usuarios, setUsuarios] = useState([]);
  const [mostrarNovoAcesso, setMostrarNovoAcesso] = useState(false);

  useEffect(() => {
    fetch('http://localhost:8080/acesso/listar')
      .then(response => response.json())
      .then(data => setUsuarios(data))
      .catch(error => console.error('Erro ao buscar usuários:', error));
  }, []);

  const adicionarUsuario = (novoUsuario) => {
    setUsuarios([...usuarios, novoUsuario]);
  };

  const editarSenhaUsuario = (index, novaSenha) => {
    const usuariosAtualizados = [...usuarios];
    usuariosAtualizados[index].senha = novaSenha;
    setUsuarios(usuariosAtualizados);
  };

  return (
    <div className="configuracao-acesso">
      <h1>Configuração de Acesso</h1>
      <button onClick={() => setMostrarNovoAcesso(!mostrarNovoAcesso)}>
        {mostrarNovoAcesso ? 'Cancelar' : 'Criar Novo Acesso'}
      </button>
      {mostrarNovoAcesso && (
        <Card title="Novo Acesso">
          <NovoAcesso adicionarUsuario={adicionarUsuario} />
        </Card>
      )}
      <Card title="Lista de Usuários">
        <ListaUsuarios usuarios={usuarios} editarSenhaUsuario={editarSenhaUsuario} />
      </Card>
    </div>
  );
}