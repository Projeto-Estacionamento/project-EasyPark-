import React, { useState, useEffect } from 'react';
import { RelatorioBase } from '../../components/RelatorioBase/RelatorioBase';

export function RelatorioAssinaturas() {
  const [assinaturas, setAssinaturas] = useState([]);
  const [filtro, setFiltro] = useState({ tipo: '', status: '' });

  useEffect(() => {
    const fetchAssinaturas = async () => {
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
    <RelatorioBase title="Assinaturas">
      <select onChange={(e) => setFiltro({ ...filtro, tipo: e.target.value })} value={filtro.tipo} className="form-select mb-3">
        <option value="">Todos os Tipos</option>
        <option value="pagamento">Pagamento</option>
        <option value="vencimento">Vencimento</option>
      </select>
      <select onChange={(e) => setFiltro({ ...filtro, status: e.target.value })} value={filtro.status} className="form-select mb-3">
        <option value="">Todos os Status</option>
        <option value="ativo">Ativo</option>
        <option value="inativo">Inativo</option>
      </select>
      <ul className="list-group">
        {assinaturasFiltradas.map((assinatura, index) => (
          <li key={index} className="list-group-item">{assinatura.nome} - {assinatura.status}</li>
        ))}
      </ul>
    </RelatorioBase>
  );
} 