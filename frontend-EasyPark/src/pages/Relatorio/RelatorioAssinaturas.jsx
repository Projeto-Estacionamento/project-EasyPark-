import React, { useState, useEffect } from 'react';

export function RelatorioAssinaturas() {
  const [assinaturas, setAssinaturas] = useState([]);
  const [filtro, setFiltro] = useState({ tipo: '', status: '' });

  useEffect(() => {
    // Fetch assinaturas do backend
    const fetchAssinaturas = async () => {
      // Simulação de fetch
      const data = await fetch('/api/assinaturas').then(res => res.json());
      setAssinaturas(data);
    };

    fetchAssinaturas();
  }, []);

  const assinaturasFiltradas = assinaturas.filter(assinatura => 
    (filtro.tipo ? assinatura.tipo === filtro.tipo : true) &&
    (filtro.status ? assinatura.status === filtro.status : true)
  );

  return (
    <div>
      <h2>Assinaturas</h2>
      <select onChange={(e) => setFiltro({ ...filtro, tipo: e.target.value })} value={filtro.tipo}>
        <option value="">Todos os Tipos</option>
        <option value="pagamento">Pagamento</option>
        <option value="vencimento">Vencimento</option>
      </select>
      <select onChange={(e) => setFiltro({ ...filtro, status: e.target.value })} value={filtro.status}>
        <option value="">Todos os Status</option>
        <option value="ativo">Ativo</option>
        <option value="inativo">Inativo</option>
      </select>
      <ul>
        {assinaturasFiltradas.map((assinatura, index) => (
          <li key={index}>{assinatura.nome} - {assinatura.status}</li>
        ))}
      </ul>
    </div>
  );
} 