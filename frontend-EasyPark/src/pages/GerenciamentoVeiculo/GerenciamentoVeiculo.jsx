import React, { useState, useEffect } from 'react';
import { ListaVeiculos } from './ListaVeiculos';
import { Veiculo } from '../novo/Veiculo';
import { fetchVeiculos, criarVeiculo } from '../../services/VeiculoService';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Card } from '../../components/card/Card';
import { Button } from '../../components/button/button';
import './GerenciamentoVeiculo.css';
// import mockData from '../../mock/mockData';
import { FiAperture } from 'react-icons/fi';
import { useNavigate } from 'react-router-dom';
import { fetchUsuarioById } from '../../services/AssinaturaService';



/** campos da tabela 
 *  id: 1,
            placa: "ABC1234",
            tipoVeiculo: "CARRO",
            ocupandoVaga: true,
            fabricante: {
              id: 1,
              modelo: "Civic",
              marca: "Honda",
              ano: 2020
            }
          }
*/

const columns = [
  { field: 'placa', header: 'Placa', width: '40px' },
  { field: 'tipoVeiculo', header: 'Tipo Veiculo', width: '40px' },
  { field: 'ocupandoVaga', header: 'Ocupando Vaga', width: '40px' },
  { field: 'fabricante', header: 'Fabricante', width: '40px' },
  { field: 'usuario', header: 'Dono', width: '40px' },
];





export function GerenciamentoVeiculo() {
  const navigate = useNavigate();
  const [veiculos, setVeiculos] = useState([]);
  const [usuarios, setUsuarios] = useState({});

  useEffect(() => {
    const carregarVeiculos = async () => {
      try {
        const data = await fetchVeiculos(); // Busca os veículos do banco de dados
        setVeiculos(data);
        
        // Buscar informações dos usuários
        const usuariosMap = {};
        for (const veiculo of data) {
          if (veiculo.idUsuarioDTO && !usuariosMap[veiculo.idUsuarioDTO]) {
            const usuario = await fetchUsuarioById(veiculo.idUsuarioDTO);
            usuariosMap[veiculo.idUsuarioDTO] = usuario;
          }
        }
        setUsuarios(usuariosMap);
      } catch (error) {
        console.error('Erro ao carregar veículos:', error);
      }
    };

    carregarVeiculos();
  }, []);

  // useEffect(() => {
  //   const carregarVeiculos = async () => {
  //     const data = mockData.veiculos;
  //     console.log(data);
  //     // const data = await fetchVeiculos();
  //     setVeiculos(data);
  //   };

  //   carregarVeiculos();
  // }, []);



  const handleNovoVeiculo = () => {
    navigate('/novo/veiculo');
  };

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="gerenciamento-veiculo-container">
        <div className="header-container">
          <h2 className="header-title">Gerenciamento de Veiculos</h2>
          <Button
            className="btn-novo-veiculo"
            onClick={handleNovoVeiculo}
          >
            <FiAperture style={{ marginRight: '10px' }} size={20} />
            Novo Veiculo
          </Button>
        </div>

        <div className="table-container">
          <table className="data-table">
            <thead>
              <tr>
                {columns.map((col) => (
                  <th key={col.field} style={{ width: col.width }}>
                    {col.header}
                  </th>
                ))}
              </tr>
            </thead>
            <tbody>
              {veiculos.map((veiculo) => (
                <tr key={veiculo.id}>
                  <td>{veiculo.placa}</td>
                  <td>{veiculo.tipoVeiculo}</td>
                  <td>{veiculo.ocupandoVaga ? 'Sim' : 'Não'}</td>
                  <td>{veiculo.fabricanteDTO?.modelo || 'N/A'}</td>
                  <td>{usuarios[veiculo.idUsuarioDTO]?.email || veiculo.idUsuarioDTO || 'N/A'}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
    // <div className="d-flex home-container">
    //   <SidebarMenu />
    //   <div className="flex-grow-1 d-flex flex-column justify-content-center align-items-center">
    //     <Card title="Gerenciamento de Veículos">
    //       <Button onClick={() => setMostrarNovoVeiculo(!mostrarNovoVeiculo)} variant="outline-light">
    //         {mostrarNovoVeiculo ? 'Cancelar' : 'Novo Veículo'}
    //       </Button>
    //       {mostrarNovoVeiculo && <NovoVeiculo adicionarVeiculo={adicionarVeiculo} />}
    //       <h2>Lista de Veículos</h2>
    //       <ListaVeiculos veiculos={veiculos} />
    //     </Card>
    //   </div>
    // </div>
  );
}

