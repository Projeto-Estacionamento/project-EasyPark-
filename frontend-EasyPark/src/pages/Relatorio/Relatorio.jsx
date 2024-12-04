import React, { useState, useEffect } from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { RelatorioBase } from '../../components/RelatorioBase/RelatorioBase';
import { Button } from '../../components/button/button';
import 'bootstrap/dist/css/bootstrap.min.css';
import { listarRelatorios, listarTicketsAbertos } from '../../services/RelatorioService';
import './Relatorio.css';

export function Relatorio() {
  const [relatorios, setRelatorios] = useState([]);
  const [tipoRelatorio, setTipoRelatorio] = useState('abertos');
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    const carregarRelatorios = async () => {
      try {
        const data = tipoRelatorio === 'fechados' ? await listarRelatorios() : await listarTicketsAbertos();
        setRelatorios(data);
      } catch (error) {
        console.error('Erro ao carregar relatórios:', error);
      }
    };

    carregarRelatorios();
  }, [tipoRelatorio]);

  const relatoriosFiltrados = relatorios.filter(relatorio => 
    relatorio.placaVeiculo === null || relatorio.placaVeiculo.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="relatorio-container d-flex">
      <SidebarMenu />
      <div className="flex-grow-1">
        <RelatorioBase title={`Relatório de Tickets ${tipoRelatorio === 'fechados' ? 'Fechados' : 'Abertos'}`}>
          <div className="table-header d-flex justify-content-between align-items-center">
            <div className="table-actions d-flex" style={{ marginRight: '20px' }}>
              <Button onClick={() => setTipoRelatorio('fechados')} variant="primary" style={{ marginRight: '20px' }}>
                Fechados
              </Button>
              <Button onClick={() => setTipoRelatorio('abertos')} variant="primary" style={{ marginLeft: '10px' }}>
                Abertos
              </Button>
            </div>
            {tipoRelatorio === 'abertos' && (
              <input 
                type="text" 
                placeholder="Pesquisar por placa..." 
                value={searchTerm} 
                onChange={(e) => setSearchTerm(e.target.value)} 
                className="form-control my-2 filter-input" 
                style={{ color: 'white', backgroundColor: '#2D2D2D', marginRight: '20px' }}
              />
            )}
          </div>
          <table className="table table-striped">
            <thead>
              <tr>
                <th>ID</th>
                {tipoRelatorio === 'fechados' ? (
                  <>
                    <th>Tipo de Ticket</th>
                    <th>Valor Total</th>
                  </>
                ) : (
                  <>
                    <th>Placa</th>
                    <th>Tipo de Ticket</th>
                    <th>Hora de Entrada</th>
                  </>
                )}
              </tr>
            </thead>
            <tbody>
              {relatoriosFiltrados.length > 0 ? (
                relatoriosFiltrados.map((relatorio) => (
                  <tr key={relatorio.id}>
                    <td>{relatorio.id}</td>
                    {tipoRelatorio === 'fechados' ? (
                      <>
                        <td>{relatorio.tipoTicket}</td>
                        <td>R$ {relatorio.valorTotalPagar ? relatorio.valorTotalPagar.toFixed(2) : '0.00'}</td>
                      </>
                    ) : (
                      <>
                        <td>{relatorio.placaVeiculo || 'N/A'}</td>
                        <td>{relatorio.tipoTicket}</td>
                        <td>{new Date(relatorio.horaChegada).toLocaleString()}</td>
                      </>
                    )}
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="4" className="text-center">Nenhum relatório encontrado</td>
                </tr>
              )}
            </tbody>
          </table>
        </RelatorioBase>
      </div>
    </div>
  );
}