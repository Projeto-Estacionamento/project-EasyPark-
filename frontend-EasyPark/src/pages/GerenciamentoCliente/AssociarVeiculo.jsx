import React, { useState } from 'react';

export function AssociarVeiculo({ cliente }) {
  const [veiculo, setVeiculo] = useState('');

  const handleAssociar = () => {
    // Lógica para associar veículo ao cliente
    console.log(`Associando veículo ${veiculo} ao cliente ${cliente.nome}`);
    setVeiculo('');
  };

  return (
    <div>
      <h2>Associar Veículo a {cliente.nome}</h2>
      <input
        type="text"
        placeholder="Placa do Veículo"
        value={veiculo}
        onChange={(e) => setVeiculo(e.target.value)}
      />
      <button onClick={handleAssociar}>Associar Veículo</button>
    </div>
  );
} 