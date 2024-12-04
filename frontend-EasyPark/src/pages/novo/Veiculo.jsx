import React, { useState, useEffect } from "react";
import { Button } from "../../components/button/button";
import { fetchUsuarios } from "../../services/AssinaturaService";
import { criarVeiculo } from "../../services/VeiculoService";
import "./veiculo.css";
import { SidebarMenu } from "../../components/sidebarMenu/SidebarMenu";
import { FiArrowLeft } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export function Veiculo() {
  const navigate = useNavigate();
  const [placa, setPlaca] = useState("");
  const [tipoVeiculo, setTipoVeiculo] = useState("");
  const [modelo, setModelo] = useState("");
  const [marca, setMarca] = useState("");
  const [ano, setAno] = useState("");
  const [idUsuario, setIdUsuario] = useState("");
  const [usuarios, setUsuarios] = useState([]);

  useEffect(() => {
    const carregarUsuarios = async () => {
      const usuariosData = await fetchUsuarios();
      setUsuarios(usuariosData);
    };

    carregarUsuarios();
  }, []);

  useEffect(() => {
    toast.info('Componente Veículo carregado!');
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const novoVeiculo = {
        placa,
        tipoVeiculo,
        ocupandoVaga: false,
        idUsuarioDTO: parseInt(idUsuario),
        fabricanteDTO: { 
          modelo, 
          marca, 
          ano: parseInt(ano)
        }
      };

      await criarVeiculo(novoVeiculo);
      toast.success('Veículo criado com sucesso!');
      setTimeout(() => {
        navigate('/gerenciamento-veiculo');
      }, 2000);
    } catch (error) {
      console.error('Erro ao criar veículo:', error);
      toast.error('Erro ao criar veículo. Por favor, tente novamente.');
    }
  };

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="novo-veiculo-container">
        <div className="novo-veiculo-header">
          <Button
            className="btn-voltar"
            onClick={() => navigate("/gerenciamento-cliente")}
          >
            <FiArrowLeft style={{ marginRight: "0.5rem" }} size={20} />
            Voltar
          </Button>
          <h2 className="novo-veiculo-title">Novo Veículo</h2>
        </div>
        <form onSubmit={handleSubmit} className="form-novo-veiculo">
          <div className="form-grid">
            <div className="form-group">
              <label>Placa</label>
              <input
                type="text"
                value={placa}
                onChange={(e) => setPlaca(e.target.value)}
                className="form-input"
                required
              />
            </div>
            <div className="form-group">
              <label>Tipo de Veículo</label>
              <select
                value={tipoVeiculo}
                onChange={(e) => setTipoVeiculo(e.target.value)}
                className="form-input"
                required
              >
                <option value="">Selecione o Tipo de Veículo</option>
                <option value="CARRO">Carro</option>
                <option value="MOTO">Moto</option>
              </select>
            </div>
            <div className="form-group">
              <label>Modelo</label>
              <input
                type="text"
                value={modelo}
                onChange={(e) => setModelo(e.target.value)}
                className="form-input"
                required
              />
            </div>
            <div className="form-group">
              <label>Marca</label>
              <input
                type="text"
                value={marca}
                onChange={(e) => setMarca(e.target.value)}
                className="form-input"
                required
              />
            </div>
            <div className="form-group">
              <label>Ano</label>
              <input
                type="text"
                value={ano}
                onChange={(e) => setAno(e.target.value)}
                className="form-input"
                required
              />
            </div>
            <div className="form-group">
              <label>Usuário</label>
              <select
                value={idUsuario}
                onChange={(e) => setIdUsuario(e.target.value)}
                className="form-input"
                required
              >
                <option value="">Selecione um Usuário</option>
                {usuarios.map((usuario) => (
                  <option key={usuario.id} value={usuario.id}>
                    {usuario.email}
                  </option>
                ))}
              </select>
            </div>
          </div>
          <div className="form-actions">
            <Button type="submit" className="btn-adicionar">
              Adicionar
            </Button>
          </div>
        </form>
        <ToastContainer position="top-right" autoClose={3000} />
      </div>
    </div>
  );
}
