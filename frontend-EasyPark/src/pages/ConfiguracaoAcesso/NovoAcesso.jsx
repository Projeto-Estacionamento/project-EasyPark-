import React, { useState } from 'react';

export function NovoAcesso({ adicionarUsuario }) {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [tipoAcesso, setTipoAcesso] = useState('caixa');
  const [isVisible, setIsVisible] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/easypark/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          nome: username,
          email: email,
          senha: senha,
          tipoAcesso: tipoAcesso.toUpperCase(),
        }),
      });

      if (response.ok) {
        console.log("Usuário criado com sucesso");
        adicionarUsuario({ username, email, senha, tipoAcesso });
        setUsername('');
        setEmail('');
        setSenha('');
        setIsVisible(false);
      } else {
        const errorData = await response.json();
        console.error("Erro ao criar usuário:", errorData);
        alert(`Erro: ${errorData.message}`);
      }
    } catch (error) {
      console.error("Erro ao criar novo acesso:", error);
      alert('Erro ao criar novo acesso');
    }
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