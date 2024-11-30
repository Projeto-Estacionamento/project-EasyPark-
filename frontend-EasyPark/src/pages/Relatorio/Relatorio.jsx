import React, { useState, useEffect } from 'react';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import 'bootstrap/dist/css/bootstrap.min.css';
import { listarRelatorios } from '../../services/RelatorioService';

export function Relatorio() {
  const [relatorios, setRelatorios] = useState([]);

  useEffect(() => {
    const carregarRelatorios = async () => {
      try {
        const data = await listarRelatorios();
        setRelatorios(data);
      } catch (error) {
        console.error('Erro ao carregar relatórios:', error);
      }
    };

    carregarRelatorios();
  }, []);

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="flex-grow-1" style={{ marginLeft: '0px', backgroundColor: '#f8f9fa', overflowY: 'auto', height: '100vh' }}>
        <div className="d-flex align-items-center justify-content-center" style={{ backgroundColor: '#00838f', color: 'white', padding: '1rem', borderRadius: '0 0 8px 8px' }}>
          <h1 className="m-0" style={{ fontSize: '2rem' }}>Relatório de Tickets Fechados</h1>
        </div>
        <div className="p-3">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>ID</th>
                <th>Tipo de Veículo</th>
                <th>Hora de Entrada</th>
                <th>Hora de Saída</th>
                <th>Valor Total</th>
              </tr>
            </thead>
            <tbody>
              {relatorios.map((relatorio) => (
                <tr key={relatorio.id}>
                  <td>{relatorio.id}</td>
                  <td>{relatorio.tipoVeiculo}</td>
                  <td>{new Date(relatorio.horaEntrada).toLocaleString()}</td>
                  <td>{new Date(relatorio.horaSaida).toLocaleString()}</td>
                  <td>R$ {relatorio.valorTotalPagar.toFixed(2)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}