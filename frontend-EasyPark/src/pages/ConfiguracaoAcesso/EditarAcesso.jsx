import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import { FiArrowLeft } from "react-icons/fi";
import { ToastContainer, toast } from 'react-toastify';
import { atualizarAcesso } from '../../services/AcessoService';
import 'react-toastify/dist/ReactToastify.css';
import './EditarAcesso.css';

export function EditarAcesso() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [senha, setSenha] = useState('');
  const [confirmarSenha, setConfirmarSenha] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (senha !== confirmarSenha) {
      toast.error('As senhas não coincidem');
      return;
    }

    try {
      await atualizarAcesso(id, { senha });
      toast.success('Senha atualizada com sucesso!');
      navigate('/configuracao-acesso');
    } catch (error) {
      console.error('Erro ao atualizar senha:', error);
      toast.error('Erro ao atualizar senha');
    }
  };

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="configuracao-acesso-container">
        <div className="header-container">
          <h2 className="header-title">Alterar Senha</h2>
          <Button
            className="btn-voltar"
            onClick={() => navigate("/configuracao-acesso")}
          >
            <div className="d-flex align-items-center">
              <FiArrowLeft style={{ marginRight: '0.5rem' }} />
              Voltar
            </div>
          </Button>
        </div>

        <div className="form-container">
          <form onSubmit={handleSubmit} className="form-padrao">
            <div className="form-group">
              <label>Nova Senha</label>
              <input
                type="password"
                value={senha}
                onChange={(e) => setSenha(e.target.value)}
                className="form-input"
                required
              />
            </div>
            <div className="form-group">
              <label>Confirmar Nova Senha</label>
              <input
                type="password"
                value={confirmarSenha}
                onChange={(e) => setConfirmarSenha(e.target.value)}
                className="form-input"
                required
              />
            </div>
            <div className="form-actions">
              <Button type="submit" className="btn-salvar">
                Salvar Alterações
              </Button>
            </div>
          </form>
        </div>
        <ToastContainer />
      </div>
    </div>
  );
}