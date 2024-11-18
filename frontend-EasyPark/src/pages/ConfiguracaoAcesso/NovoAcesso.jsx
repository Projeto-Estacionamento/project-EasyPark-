import React, { useState } from 'react';
import { PageContainer } from '../../components/pageContainer/PageContainer';
import './NovoAcesso.css';

export function NovoAcesso({ adicionarUsuario }) {
  const [username, setUsername] = useState('');
  const [senha, setSenha] = useState('');
  const [tipoAcesso, setTipoAcesso] = useState('caixa');
  const [isVisible, setIsVisible] = useState(true);

  const handleSubmit = (e) => {
    e.preventDefault();
    adicionarUsuario({ username, senha, tipoAcesso });
    setUsername('');
    setSenha('');
  };

  const handleClose = () => {
    setIsVisible(false);
  };

  if (!isVisible) return null;

  return (
    <div className="novo-acesso-container">
      <PageContainer darkMode>
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
        </div>
      </PageContainer>
    </div>
  );
} 