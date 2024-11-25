import React, { useState, useEffect } from 'react';
import { ListaAssinaturas } from './ListaAssinaturas';
import { NovaAssinatura } from './NovaAssinatura';
import { fetchAssinaturas, criarAssinatura, atualizarStatusAssinatura } from '../../services/AssinaturaService';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Card } from '../../components/card/Card';
import { Button } from '../../components/button/button';
import './GerenciamentoAssinaturaPlano.css';

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
    <div className="d-flex home-container">
      <SidebarMenu />
      <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center">
        <Card title="Gerenciamento de Assinatura">
          <Button onClick={() => setMostrarNovaAssinatura(!mostrarNovaAssinatura)} variant={mostrarNovaAssinatura ? 'outline-light' : 'outline-light'}>
            {mostrarNovaAssinatura ? 'Cancelar' : 'Nova Assinatura'}
          </Button>
          {mostrarNovaAssinatura && <NovaAssinatura adicionarAssinatura={adicionarAssinatura} />}
          <h2>Lista de Assinaturas</h2>
          <select onChange={(e) => setFiltroStatus(e.target.value)} value={filtroStatus} className="form-control mb-3">
            <option value="">Todos os Status</option>
            <option value="true">Ativo</option>
            <option value="false">Inativo</option>
          </select>
          <ListaAssinaturas assinaturas={assinaturasFiltradas} alterarStatusAssinatura={alterarStatusAssinatura} />
        </Card>
      </div>
    </div>
  );
}