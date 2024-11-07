import React from 'react';

export function ListaUsuarios({ usuarios }) {
  return (
    <div>
      <h2>Lista de Usuários</h2>
      <ul>
        {usuarios.map((usuario, index) => (
          <li key={index}>
            {usuario.username} - {usuario.tipoAcesso}
            <button>Editar Senha</button>
          </li>
        ))}
      </ul>
    </div>
  );
} 