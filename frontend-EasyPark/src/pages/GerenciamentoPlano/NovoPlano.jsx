import React, { useState } from 'react';

export function NovoPlano({ adicionarPlano }) {
  const [tipoPlano, setTipoPlano] = useState('INTEGRAL');
  const [tipoVeiculo, setTipoVeiculo] = useState('CARRO');
  const [valorPlano, setValorPlano] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    adicionarPlano({ tipoPlano, tipoVeiculo, valorPlano: parseFloat(valorPlano) });
    setTipoPlano('INTEGRAL');
    setTipoVeiculo('CARRO');
    setValorPlano('');
  };

  return (
    <div>
      <h2>Novo Plano</h2>
      <form onSubmit={handleSubmit}>
        <select value={tipoPlano} onChange={(e) => setTipoPlano(e.target.value)}>
          <option value="INTEGRAL">Integral</option>
          <option value="MANHA">Manhã</option>
          <option value="TARDE">Tarde</option>
        </select>
        <select value={tipoVeiculo} onChange={(e) => setTipoVeiculo(e.target.value)}>
          <option value="CARRO">Carro</option>
          <option value="MOTO">Moto</option>
        </select>
        <input
          type="number"
          placeholder="Valor do Plano"
          value={valorPlano}
          onChange={(e) => setValorPlano(e.target.value)}
          step="0.01"
        />
        <button type="submit">Adicionar</button>
      </form>
    </div>
  );
} 