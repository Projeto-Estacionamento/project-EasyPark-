import React from 'react';

export function ListaUsuarios({ usuarios, editarSenhaUsuario }) {
  return (
    <div>
      <h2>Usuários</h2>
      <ul>
        {usuarios.map((usuario, index) => (
          <li key={index}>
            <span>{usuario.username} - {usuario.tipoAcesso}</span>
            <button onClick={() => {
              const novaSenha = prompt('Digite a nova senha:');
              if (novaSenha) {
                editarSenhaUsuario(index, novaSenha);
              }
            }}>
              Editar Senha
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}