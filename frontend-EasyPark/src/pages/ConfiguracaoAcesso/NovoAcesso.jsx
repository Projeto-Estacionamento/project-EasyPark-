import React, { useState } from 'react';
import { Button } from '../../components/button/button';

export function NovoAcesso({ adicionarUsuario }) {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [tipoAcesso, setTipoAcesso] = useState('caixa');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const token = sessionStorage.getItem('token');
      console.log('Token:', token);

      const response = await fetch('http://localhost:8080/easypark/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({
          email: email,
          senha: senha,
          tipoAcesso: tipoAcesso.toUpperCase(),
        }),
      });

      console.log('Response status:', response.status);

      if (response.ok) {
        console.log("Usuário criado com sucesso");
        adicionarUsuario({ email, senha, tipoAcesso });
        setEmail('');
        setSenha('');
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

  return (
    <div className="novo-acesso-card" style={{ padding: '20px' }}>
      <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          autoComplete="email"
        />
        <input
          type="password"
          placeholder="Senha"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          autoComplete="new-password"
        />
        <select value={tipoAcesso} onChange={(e) => setTipoAcesso(e.target.value)}>
          <option value="caixa">Caixa</option>
          <option value="administrador">Administrador</option>
        </select>
        <Button type="submit" variant="outline-light" fullWidth>
          Criar Novo Acesso
        </Button>
      </form>
    </div>
  );
} 