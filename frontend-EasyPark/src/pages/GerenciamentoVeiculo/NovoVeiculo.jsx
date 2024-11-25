import React, { useState, useEffect } from 'react';
import { Button } from '../../components/button/button';
import { fetchUsuarios } from '../../services/AssinaturaService';

export function NovoVeiculo({ adicionarVeiculo }) {
  const [placa, setPlaca] = useState('');
  const [tipoVeiculo, setTipoVeiculo] = useState('');
  const [modelo, setModelo] = useState('');
  const [marca, setMarca] = useState('');
  const [ano, setAno] = useState('');
  const [idUsuario, setIdUsuario] = useState('');
  const [usuarios, setUsuarios] = useState([]);

  useEffect(() => {
    const carregarUsuarios = async () => {
      const usuariosData = await fetchUsuarios();
      setUsuarios(usuariosData);
    };

    carregarUsuarios();
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    adicionarVeiculo({
      placa,
      tipoVeiculo,
      ocupandoVaga: false,
      idUsuarioDTO: idUsuario,
      fabricanteDTO: { modelo, marca, ano }
    });
    setPlaca('');
    setTipoVeiculo('');
    setModelo('');
    setMarca('');
    setAno('');
    setIdUsuario('');
  };

  return (
    <div>
      <h2>Novo Veículo</h2>
      <form onSubmit={handleSubmit} className="form-novo-veiculo">
        <input type="text" placeholder="Placa" value={placa} onChange={(e) => setPlaca(e.target.value)} className="form-control mb-3" />
        <select
          value={tipoVeiculo}
          onChange={(e) => setTipoVeiculo(e.target.value)}
          className="form-control mb-3"
        >
          <option value="">Selecione o Tipo de Veículo</option>
          <option value="CARRO">Carro</option>
          <option value="MOTO">Moto</option>
        </select>
        <input type="text" placeholder="Modelo" value={modelo} onChange={(e) => setModelo(e.target.value)} className="form-control mb-3" />
        <input type="text" placeholder="Marca" value={marca} onChange={(e) => setMarca(e.target.value)} className="form-control mb-3" />
        <input type="text" placeholder="Ano" value={ano} onChange={(e) => setAno(e.target.value)} className="form-control mb-3" />
        <select
          value={idUsuario}
          onChange={(e) => setIdUsuario(e.target.value)}
          className="form-control mb-3"
        >
          <option value="">Selecione um Usuário</option>
          {usuarios.map((usuario) => (
            <option key={usuario.id} value={usuario.id}>
              {usuario.email}
            </option>
          ))}
        </select>
        <div className="d-flex justify-content-center">
          <Button type="submit" variant="outline-light">Adicionar</Button>
        </div>
      </form>
    </div>
  );
}
