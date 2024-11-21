import React, { useState } from 'react';
import { Button } from '../../components/button/button';

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
      <form onSubmit={handleSubmit} className="form-nova-assinatura">
        <input
          type="text"
          placeholder="ID do Usuário"
          value={usuarioId}
          onChange={(e) => setUsuarioId(e.target.value)}
          className="form-control mb-3"
        />
        <input
          type="text"
          placeholder="ID do Plano"
          value={planoId}
          onChange={(e) => setPlanoId(e.target.value)}
          className="form-control mb-3"
        />
        <div className="d-flex justify-content-center">
          <Button type="submit" variant="outline-light">Adicionar</Button>
        </div>
      </form>
    </div>
  );
} 