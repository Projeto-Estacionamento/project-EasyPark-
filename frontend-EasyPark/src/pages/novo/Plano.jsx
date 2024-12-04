import React, { useState } from 'react';
import { Button } from '../../components/button/button';
import { SidebarMenu } from "../../components/sidebarMenu/SidebarMenu";
import { FiArrowLeft } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
import { criarPlano } from "../../services/PlanoService";
import "./plano.css";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
export function Plano() {
  const navigate = useNavigate();
  const [tipoPlano, setTipoPlano] = useState('INTEGRAL');
  const [tipoVeiculo, setTipoVeiculo] = useState('CARRO');
  const [valorPlano, setValorPlano] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const novoPlano = {
      tipoPlano,
      tipoVeiculo,
      valorPlano: parseFloat(valorPlano)
    };
    await criarPlano(novoPlano);
    toast.success('Plano criado com sucesso!');
    setTipoPlano('INTEGRAL');
    setTipoVeiculo('CARRO');
    setValorPlano('');
    setTimeout(() => {
      navigate('/gerenciamento-plano');
    }, 2000);
  };

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="novo-plano-container">
        <div className="novo-plano-header">
          <Button
            className="btn-voltar"
            onClick={() => navigate("/gerenciamento-plano")}
          >
            <FiArrowLeft style={{ marginRight: "0.5rem" }} size={20} />
            Voltar
          </Button>
          <h2 className="novo-plano-title">Novo Plano</h2>
        </div>
        <form onSubmit={handleSubmit} className="form-novo-plano">
          <div className="form-grid">
            <div className="form-group">
              <label>Tipo de Plano</label>
              <select
                value={tipoPlano}
                onChange={(e) => setTipoPlano(e.target.value)}
                className="form-input"
                required
              >
                <option value="INTEGRAL">Integral</option>
                <option value="MANHA">Manhã</option>
                <option value="TARDE">Tarde</option>
                <option value="NOITE">Noite</option>
              </select>
            </div>
            <div className="form-group">
              <label>Tipo de Veículo</label>
              <select
                value={tipoVeiculo}
                onChange={(e) => setTipoVeiculo(e.target.value)}
                className="form-input"
                required
              >
                <option value="CARRO">Carro</option>
                <option value="MOTO">Moto</option>
              </select>
            </div>
            <div className="form-group">
              <label>Valor do Plano</label>
              <input
                type="number"
                placeholder="Valor do Plano"
                value={valorPlano}
                onChange={(e) => setValorPlano(e.target.value)}
                step="0.01"
                className="form-input"
                required
              />
            </div>
          </div>
          <div className="form-actions">
            <Button type="submit" className="btn-adicionar">Adicionar</Button>
          </div>
        </form>
      </div>
    </div>
  );
} 