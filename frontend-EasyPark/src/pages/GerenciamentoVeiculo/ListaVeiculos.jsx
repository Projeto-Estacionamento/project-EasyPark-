import React, { useState, useEffect } from 'react';
import { Button } from '../../components/button/button';
import { fetchUsuarios } from '../../services/AssinaturaService';

export function ListaVeiculos({ veiculos }) {
  const [tipoFiltro, setTipoFiltro] = useState('');
  const [usuarioFiltro, setUsuarioFiltro] = useState('');
  const [usuarios, setUsuarios] = useState([]);
  const [veiculosFiltrados, setVeiculosFiltrados] = useState(veiculos);

  useEffect(() => {
    const carregarUsuarios = async () => {
      const usuariosData = await fetchUsuarios();
      setUsuarios(usuariosData);
    };

    carregarUsuarios();
  }, []);

  const aplicarFiltro = () => {
    setVeiculosFiltrados(
      veiculos.filter((veiculo) => 
        (tipoFiltro ? veiculo.tipoVeiculo === tipoFiltro : true) &&
        (usuarioFiltro ? veiculo.idUsuarioDTO === parseInt(usuarioFiltro) : true)
      )
    );
  };

  const removerFiltro = () => {
    setTipoFiltro('');
    setUsuarioFiltro('');
    setVeiculosFiltrados(veiculos);
  };

  return (
    <div>
      <div className="d-flex mb-3 align-items-center">
        <select
          value={tipoFiltro}
          onChange={(e) => setTipoFiltro(e.target.value)}
          className="form-control mr-2"
          style={{ width: '150px', marginRight: '10px' }}
        >
          <option value="">Todos os Tipos</option>
          <option value="CARRO">Carro</option>
          <option value="MOTO">Moto</option>
        </select>
        <select
          value={usuarioFiltro}
          onChange={(e) => setUsuarioFiltro(e.target.value)}
          className="form-control mr-2"
          style={{ width: '200px', marginRight: '10px' }}
        >
          <option value="">Todos os Usuários</option>
          {usuarios.map((usuario) => (
            <option key={usuario.id} value={usuario.id}>
              {usuario.email}
            </option>
          ))}
        </select>
        <Button onClick={aplicarFiltro} variant="outline-light" style={{ marginRight: '10px' }}>Filtrar</Button>
        <Button onClick={removerFiltro} variant="outline-light">Remover Filtro</Button>
      </div>
      <ul>
        {veiculosFiltrados.map((veiculo) => (
          <li key={veiculo.id}>
            {veiculo.placa} - {veiculo.tipoVeiculo} - {veiculo.fabricanteDTO.marca} {veiculo.fabricanteDTO.modelo} ({veiculo.fabricanteDTO.ano})
          </li>
        ))}
      </ul>
    </div>
  );
}
