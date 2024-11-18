import React, { useState } from 'react';

export function NovoAcesso({ adicionarUsuario }) {
  const [username, setUsername] = useState('');
  const [senha, setSenha] = useState('');
  const [tipoAcesso, setTipoAcesso] = useState('caixa');

  const handleSubmit = (e) => {
    e.preventDefault();
    adicionarUsuario({ username, senha, tipoAcesso });
    setUsername('');
    setSenha('');
  };

  return (
    <div>
      <PageContainer darkMode>
      <h2>Novo Acesso</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Usuário"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Senha"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
        />
        <select value={tipoAcesso} onChange={(e) => setTipoAcesso(e.target.value)}>
          <option value="caixa">Caixa</option>
          <option value="administrador">Administrador</option>
        </select>
        <button type="submit">Adicionar</button>
      </form>
      </PageContainer>
    </div>
  );
} 