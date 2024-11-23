import React, { useState } from 'react';

export function NovoAcesso({ adicionarUsuario }) {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [tipoAcesso, setTipoAcesso] = useState('caixa');
  const [isVisible, setIsVisible] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();
    adicionarUsuario({ username, email, senha, tipoAcesso });
    setUsername('');
    setEmail('');
    setSenha('');
    setIsVisible(false);
  };

  const handleClose = () => {
    setIsVisible(false);
  };

  return (
    <div className="lista-usuarios-card">
      <button onClick={() => setIsVisible(true)}>Criar Novo Acesso</button>
      <h2>Lista de Usuários</h2>
      {isVisible && (
        <div className="novo-acesso-card">
          <button className="close-button" onClick={handleClose}>×</button>
          <h2>Novo Acesso</h2>
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              placeholder="Usuário"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
            <input
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
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
            <button type="submit">Criar Novo Acesso</button>
          </form>
        </div>
      )}
    </div>
  );
} 