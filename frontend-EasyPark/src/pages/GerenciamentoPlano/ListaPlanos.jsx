import React from 'react';

export function ListaPlanos({ planos }) {
  return (
    <div>
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