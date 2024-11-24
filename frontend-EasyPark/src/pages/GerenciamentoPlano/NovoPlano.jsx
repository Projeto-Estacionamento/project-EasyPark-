import React, { useState } from 'react';
import { Button } from '../../components/button/button';

export function NovoPlano({ adicionarPlano }) {
  const [tipoPlano, setTipoPlano] = useState('INTEGRAL');
  const [tipoVeiculo, setTipoVeiculo] = useState('CARRO');
  const [valorPlano, setValorPlano] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const novoPlano = {
      tipoPlano,
      tipoVeiculo,
      valorPlano: parseFloat(valorPlano)
    };
    adicionarPlano(novoPlano);
    setTipoPlano('INTEGRAL');
    setTipoVeiculo('CARRO');
    setValorPlano('');
  };

  return (
    <div>
      <h2>Novo Plano</h2>
      <form onSubmit={handleSubmit} className="form-novo-plano">
        <select value={tipoPlano} onChange={(e) => setTipoPlano(e.target.value)} className="form-control mb-3">
          <option value="INTEGRAL">Integral</option>
          <option value="MANHA">Manhã</option>
          <option value="TARDE">Tarde</option>
          <option value="NOITE">Noite</option>
        </select>
        <select value={tipoVeiculo} onChange={(e) => setTipoVeiculo(e.target.value)} className="form-control mb-3">
          <option value="CARRO">Carro</option>
          <option value="MOTO">Moto</option>
        </select>
        <input
          type="number"
          placeholder="Valor do Plano"
          value={valorPlano}
          onChange={(e) => setValorPlano(e.target.value)}
          step="0.01"
          className="form-control mb-3"
        />
        <div className="d-flex justify-content-center">
          <Button type="submit" variant="outline-light">Adicionar</Button>
        </div>
      </form>
    </div>
  );
} 