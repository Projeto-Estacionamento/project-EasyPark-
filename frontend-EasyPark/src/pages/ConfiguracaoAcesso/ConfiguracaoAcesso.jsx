import React, { useState } from 'react';
import './ConfiguracaoAcesso.css';
import { ListaUsuarios } from './ListaUsuarios';
import { NovoAcesso } from './NovoAcesso';

export function ConfiguracaoAcesso() {
  const [usuarios, setUsuarios] = useState([]);
  const [mostrarNovoAcesso, setMostrarNovoAcesso] = useState(false);

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
      {mostrarNovoAcesso && <NovoAcesso adicionarUsuario={adicionarUsuario} />}
      <ListaUsuarios usuarios={usuarios} editarSenhaUsuario={editarSenhaUsuario} />
    </div>
  );
}
