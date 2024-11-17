import React from 'react';

export function ListaPlanos({ planos }) {
  return (
    <div>
      <h2>Lista de Planos</h2>
      <ul>
        {planos.map((plano) => (
          <li key={plano.id}>
            {plano.tipoPlano} - {plano.tipoVeiculo} - R${plano.valorPlano.toFixed(2)}
          </li>
        ))}
      </ul>
    </div>
  );
} 