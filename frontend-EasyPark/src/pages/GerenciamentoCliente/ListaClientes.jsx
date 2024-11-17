import React from 'react';

export function ListaClientes({ clientes, setClienteSelecionado }) {
  return (
    <div>
      <h2>Lista de Clientes</h2>
      <ul>
        {clientes.map((cliente) => (
          <li key={cliente.id}>
            {cliente.nome} - {cliente.email}
            <button onClick={() => setClienteSelecionado(cliente)}>Selecionar</button>
          </li>
        ))}
      </ul>
    </div>
  );
} 