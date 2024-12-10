import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';
import { FiArrowLeft } from "react-icons/fi";
import { ToastContainer, toast } from 'react-toastify';
import { atualizarAcesso, buscarAcessoPorId } from '../../services/AcessoService';
import 'react-toastify/dist/ReactToastify.css';
import './EditarAcesso.css';

export function EditarAcesso() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [confirmarSenha, setConfirmarSenha] = useState('');
  const [tipoAcesso, setTipoAcesso] = useState('');
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    const carregarDados = async () => {
      try {
        const dados = await buscarAcessoPorId(id);
        setEmail(dados.email || '');
        setTipoAcesso(dados.tipoAcesso || 'CAIXA');
        setCarregando(false);
      } catch (error) {
        console.error('Erro ao carregar dados do usuário:', error);
        toast.error('Erro ao carregar dados do usuário');
        setCarregando(false);
      }
    };

    carregarDados();
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if ((senha || confirmarSenha) && senha !== confirmarSenha) {
      toast.error('As senhas não coincidem');
      return;
    }

    try {
      const token = sessionStorage.getItem('token');
      if (!token) {
        toast.error('Usuário não autenticado');
        return;
      }

      const dadosAtualizacao = {
        email,
        tipoAcesso
      };

      if (senha) {
        dadosAtualizacao.senha = senha;
      }

      await atualizarAcesso(id, dadosAtualizacao);
      
      toast.success('Acesso Alterado!');
      setTimeout(() => {
        navigate('/configuracao-acesso');
      }, 2000);
    } catch (error) {
      console.error('Erro ao atualizar dados:', error);
      toast.error('Erro ao atualizar dados: ' + (error.response?.data?.message || 'Erro desconhecido'));
    }
  };

  if (carregando) {
    return (
      <div className="d-flex">
        <SidebarMenu />
        <div className="configuracao-acesso-container">
          <div className="loading">Carregando dados...</div>
        </div>
      </div>
    );
  }

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="configuracao-acesso-container">
        <div className="header-container">
          <h2 className="header-title">Alterar Dados</h2>
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
              <label>Email</label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="form-input"
                required
              />
            </div>
            <div className="form-group">
              <label>Nova Senha (opcional)</label>
              <input
                type="password"
                value={senha}
                onChange={(e) => setSenha(e.target.value)}
                className="form-input"
                placeholder="Deixe em branco para manter a senha atual"
              />
            </div>
            <div className="form-group">
              <label>Confirmar Nova Senha (opcional)</label>
              <input
                type="password"
                value={confirmarSenha}
                onChange={(e) => setConfirmarSenha(e.target.value)}
                className="form-input"
                placeholder="Deixe em branco para manter a senha atual"
              />
            </div>
            <div className="form-actions">
              <Button type="submit" className="btn-salvar">
                Salvar Alterações
              </Button>
            </div>
          </form>
        </div>
        <ToastContainer position="top-right" autoClose={3000} />
      </div>
    </div>
  );
}