import React, { useState, useEffect } from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { RelatorioBase } from '../../components/RelatorioBase/RelatorioBase';
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
          <div className="p-3">
            <div className="mb-3">
              <button onClick={() => setTipoRelatorio('fechados')} className="btn btn-primary me-2">Fechados</button>
              <button onClick={() => setTipoRelatorio('abertos')} className="btn btn-secondary">Abertos</button>
            </div>
            <input 
              type="text" 
              placeholder="Pesquisar por placa..." 
              value={searchTerm} 
              onChange={(e) => setSearchTerm(e.target.value)} 
              className="form-control my-2"
            />
            <table className="table table-striped">
              <thead>
                <tr>
                  {tipoRelatorio === 'fechados' ? (
                    <>
                      <th>ID</th>
                      <th>Placa</th>
                      <th>Tipo de Ticket</th>
                      <th>Valor Total</th>
                    </>
                  ) : (
                    <>
                      <th>ID</th>
                      <th>Placa</th>
                      <th>Tipo de Ticket</th>
                      <th>Tipo de Veículo</th>
                    </>
                  )}
                </tr>
              </thead>
              <tbody>
                {relatoriosFiltrados.length > 0 ? (
                  relatoriosFiltrados.map((relatorio) => (
                    <tr key={relatorio.id}>
                      {tipoRelatorio === 'fechados' ? (
                        <>
                          <td>{relatorio.id}</td>
                          <td>{relatorio.placaVeiculo || 'N/A'}</td>
                          <td>{relatorio.tipoTicket}</td>
                          <td>R$ {relatorio.valorTotalPagar ? relatorio.valorTotalPagar.toFixed(2) : '0.00'}</td>
                        </>
                      ) : (
                        <>
                          <td>{relatorio.id}</td>
                          <td>{relatorio.placaVeiculo}</td>
                          <td>{relatorio.tipoTicket}</td>
                          <td>{relatorio.tipoVeiculo}</td>
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
          </div>
        </RelatorioBase>
      </div>
    </div>
  );
}