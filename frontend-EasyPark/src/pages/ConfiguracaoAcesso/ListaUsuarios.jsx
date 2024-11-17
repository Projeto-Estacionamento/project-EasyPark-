import React, { useState } from 'react';

export function ListaUsuarios({ usuarios, editarSenhaUsuario }) {
  const [senhaEditando, setSenhaEditando] = useState(null);
  const [novaSenha, setNovaSenha] = useState('');

  const handleEditarSenha = (index) => {
    editarSenhaUsuario(index, novaSenha);
    setSenhaEditando(null);
    setNovaSenha('');
  };

  return (
    <div>
      <h2>Lista de Usuários</h2>
      <ul>
        {usuarios.map((usuario, index) => (
          <li key={index}>
            {usuario.username} - {usuario.tipoAcesso}
            {senhaEditando === index ? (
              <>
                <input
                  type="password"
                  value={novaSenha}
                  onChange={(e) => setNovaSenha(e.target.value)}
                />
                <button onClick={() => handleEditarSenha(index)}>Salvar</button>
                <button onClick={() => setSenhaEditando(null)}>Cancelar</button>
              </>
            ) : (
              <button onClick={() => setSenhaEditando(index)}>Editar Senha</button>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
} 