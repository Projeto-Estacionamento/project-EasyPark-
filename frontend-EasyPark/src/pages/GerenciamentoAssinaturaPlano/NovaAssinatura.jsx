import React, { useState, useEffect } from 'react';
import { Button } from '../../components/button/button';
import { fetchPlanos, fetchUsuarios } from '../../services/AssinaturaService';

export function NovaAssinatura({ adicionarAssinatura }) {
  const [usuarioId, setUsuarioId] = useState('');
  const [planoId, setPlanoId] = useState('');
  const [planos, setPlanos] = useState([]);
  const [usuarios, setUsuarios] = useState([]);

  useEffect(() => {
    const carregarDados = async () => {
      const planosData = await fetchPlanos();
      setPlanos(planosData);

      const usuariosData = await fetchUsuarios();
      setUsuarios(usuariosData);
    };

    carregarDados();
  }, []);

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
        <select
          value={usuarioId}
          onChange={(e) => setUsuarioId(e.target.value)}
          className="form-control mb-3"
        >
          <option value="">Selecione um Usuário</option>
          {usuarios.map((usuario) => (
            <option key={usuario.id} value={usuario.id}>
              {usuario.email}
            </option>
          ))}
        </select>
        <select
          value={planoId}
          onChange={(e) => setPlanoId(e.target.value)}
          className="form-control mb-3"
        >
          <option value="">Selecione um Plano</option>
          {planos.map((plano) => (
            <option key={plano.id} value={plano.id}>
              {`${plano.tipoPlano} - ${plano.tipoVeiculo} - R$${plano.valorPlano}`}
            </option>
          ))}
        </select>
        <div className="d-flex justify-content-center">
          <Button type="submit" variant="outline-light">Adicionar</Button>
        </div>
      </form>
    </div>
  );
} 