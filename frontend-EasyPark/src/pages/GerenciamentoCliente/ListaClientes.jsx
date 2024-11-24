import React, { useState } from 'react';
import { Button } from '../../components/button/button';

export function NovoCliente({ adicionarCliente }) {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [telefone, setTelefone] = useState('');
  const [cpf, setCpf] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    adicionarCliente({ nome, email, telefone, cpf });
    setNome('');
    setEmail('');
    setTelefone('');
    setCpf('');
  };

  return (
    <div>
      <h2>Novo Cliente</h2>
      <form onSubmit={handleSubmit} className="form-novo-cliente">
        <input
          type="text"
          placeholder="Nome"
          value={nome}
          onChange={(e) => setNome(e.target.value)}
          className="form-control mb-3"
        />
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="form-control mb-3"
        />
        <input
          type="text"
          placeholder="Telefone"
          value={telefone}
          onChange={(e) => setTelefone(e.target.value)}
          className="form-control mb-3"
        />
        <input
          type="text"
          placeholder="CPF"
          value={cpf}
          onChange={(e) => setCpf(e.target.value)}
          className="form-control mb-3"
        />
        <Button type="submit" variant="outline-light">Adicionar</Button>
      </form>
    </div>
  );
} 