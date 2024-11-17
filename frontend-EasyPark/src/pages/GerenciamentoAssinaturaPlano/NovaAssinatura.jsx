import React, { useState } from 'react';

export function NovaAssinatura({ adicionarAssinatura }) {
  const [usuarioId, setUsuarioId] = useState('');
  const [planoId, setPlanoId] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    adicionarAssinatura({ usuarioDTO: { id: usuarioId }, planoDTO: { id: planoId } });
    setUsuarioId('');
    setPlanoId('');
  };

  return (
    <div>
      <h2>Nova Assinatura</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="ID do Usuário"
          value={usuarioId}
          onChange={(e) => setUsuarioId(e.target.value)}
        />
        <input
          type="text"
          placeholder="ID do Plano"
          value={planoId}
          onChange={(e) => setPlanoId(e.target.value)}
        />
        <button type="submit">Adicionar</button>
      </form>
    </div>
  );
} 