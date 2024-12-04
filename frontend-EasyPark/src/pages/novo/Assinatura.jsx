import React, { useState, useEffect } from 'react';
import { Button } from '../../components/button/button';
import { fetchPlanos, fetchUsuarios, criarAssinatura } from '../../services/AssinaturaService';
import { SidebarMenu } from "../../components/sidebarMenu/SidebarMenu";
import { FiArrowLeft } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
import "./assinatura.css";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
// import mockData from '../../mock/mockData';

export function Assinatura() {
  const navigate = useNavigate();
  const [usuarioId, setUsuarioId] = useState('');
  const [planoId, setPlanoId] = useState('');
  const [planos, setPlanos] = useState([]);
  const [usuarios, setUsuarios] = useState([]);

  // useEffect(() => {
  //   const carregarDados = async () => {
  //     // const planosData = await fetchPlanos();
  //     const planosData = mockData.planos;
  //     setPlanos(planosData);

  //     // const usuariosData = await fetchUsuarios();
  //     const usuariosData = mockData.usuarios;
  //     setUsuarios(usuariosData);
  //   };

  //   carregarDados();
  // }, []);

  useEffect(() => {
    const carregarDados = async () => {
      try {
        const planosData = await fetchPlanos();
        console.log('Planos carregados:', planosData);
        setPlanos(planosData);

        const usuariosData = await fetchUsuarios();
        console.log('Usuários carregados:', usuariosData);
        setUsuarios(usuariosData);
      } catch (error) {
        console.error('Erro ao carregar dados:', error);
      }
    };

    carregarDados();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const novaAssinatura = {
        usuarioDTO: { id: parseInt(usuarioId) },
        planoDTO: { id: parseInt(planoId) }
      };
      
      await criarAssinatura(novaAssinatura);
      toast.success('Assinatura criada com sucesso!');
      setTimeout(() => {
        navigate('/gerenciamento-assinatura-plano');
      }, 2000);
    } catch (error) {
      console.error('Erro ao criar assinatura:', error);
      toast.error('Erro ao criar assinatura. Por favor, tente novamente.');
    }
  };

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="nova-assinatura-container">
        <div className="nova-assinatura-header">
          <Button
            className="btn-voltar"
            onClick={() => navigate("/gerenciamento-assinatura-plano")}
          >
            <FiArrowLeft style={{ marginRight: "0.5rem" }} size={20} />
            Voltar
          </Button>
          <h2 className="nova-assinatura-title">Nova Assinatura</h2>
        </div>
        <form onSubmit={handleSubmit} className="form-nova-assinatura">
          <div className="form-grid">
            <div className="form-group">
              <label>Usuário</label>
              <select
                value={usuarioId}
                onChange={(e) => setUsuarioId(e.target.value)}
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
            <div className="form-group">
              <label>Plano</label>
              <select
                value={planoId}
                onChange={(e) => setPlanoId(e.target.value)}
                className="form-input"
                required
              >
                <option value="">Selecione um Plano</option>
                {planos.map((plano) => (
                  <option key={plano.id} value={plano.id}>
                    {`${plano.tipoPlano} - ${plano.tipoVeiculo} - R$${plano.valorPlano}`}
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
      </div>
    </div>
  );
} 