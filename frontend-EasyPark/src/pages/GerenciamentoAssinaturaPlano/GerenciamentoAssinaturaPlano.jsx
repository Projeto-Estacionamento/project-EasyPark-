import React, { useState, useEffect } from 'react';
import { ListaAssinaturas } from './ListaAssinaturas';
import { NovaAssinatura } from './NovaAssinatura';
import { fetchAssinaturas, criarAssinatura, atualizarStatusAssinatura } from '../../services/AssinaturaService';

export function GerenciamentoAssinaturaPlano() {
  const [assinaturas, setAssinaturas] = useState([]);
  const [mostrarNovaAssinatura, setMostrarNovaAssinatura] = useState(false);
  const [filtroStatus, setFiltroStatus] = useState('');

  useEffect(() => {
    const carregarAssinaturas = async () => {
      const data = await fetchAssinaturas();
      setAssinaturas(data);
    };

    carregarAssinaturas();
  }, []);

  const adicionarAssinatura = async (novaAssinatura) => {
    const assinaturaCriada = await criarAssinatura(novaAssinatura);
    setAssinaturas([...assinaturas, assinaturaCriada]);
  };

  const alterarStatusAssinatura = async (id, novoStatus) => {
    const assinaturaAtualizada = await atualizarStatusAssinatura(id, novoStatus);
    setAssinaturas(assinaturas.map(assinatura => 
      assinatura.id === id ? assinaturaAtualizada : assinatura
    ));
  };

  const assinaturasFiltradas = assinaturas.filter(assinatura => 
    filtroStatus ? assinatura.ativo.toString() === filtroStatus : true
  );

  return (
    <div className="gerenciamento-assinatura-plano">
      <h1>Gerenciamento de Assinatura de Plano</h1>
      <select onChange={(e) => setFiltroStatus(e.target.value)} value={filtroStatus}>
        <option value="">Todos os Status</option>
        <option value="true">Ativo</option>
        <option value="false">Inativo</option>
      </select>
      <button onClick={() => setMostrarNovaAssinatura(!mostrarNovaAssinatura)}>
        {mostrarNovaAssinatura ? 'Cancelar' : 'Criar Nova Assinatura'}
      </button>
      {mostrarNovaAssinatura && <NovaAssinatura adicionarAssinatura={adicionarAssinatura} />}
      <ListaAssinaturas assinaturas={assinaturasFiltradas} alterarStatusAssinatura={alterarStatusAssinatura} />
    </div>
  );
}