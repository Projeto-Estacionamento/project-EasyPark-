import React, { useState } from 'react';
import { Button } from '../../components/button/button';
import { SidebarMenu } from "../../components/sidebarMenu/SidebarMenu";
import { FiArrowLeft } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import "./acesso.css";

export function Acesso() {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [tipoAcesso, setTipoAcesso] = useState('CAIXA');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const token = sessionStorage.getItem('token');
      
      const response = await fetch('http://localhost:8080/easypark/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({
          email,
          senha,
          tipoAcesso: tipoAcesso.toUpperCase(),
        }),
      });

      if (response.ok) {
        toast.success("Usuário criado com sucesso!");
        setEmail('');
        setSenha('');
        setTipoAcesso('CAIXA');
      } else {
        const errorData = await response.json();
        toast.error(`Erro: ${errorData.message}`);
      }
    } catch (error) {
      toast.error('Erro ao criar novo acesso');
    }
  };

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="novo-acesso-container">
        <div className="novo-acesso-header">
          <Button
            className="btn-voltar"
            onClick={() => navigate("/configuracao-acesso")}
          >
            <FiArrowLeft style={{ marginRight: "0.5rem" }} size={20} />
            Voltar
          </Button>
          <h2 className="novo-acesso-title">Novo Acesso</h2>
        </div>

        <form onSubmit={handleSubmit} className="form-novo-acesso">
          <div className="form-grid">
            <div className="form-group">
              <label>Email</label>
              <input
                type="email"
                placeholder="Digite o email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="form-input"
                required
                autoComplete="email"
              />
            </div>
            <div className="form-group">
              <label>Senha</label>
              <input
                type="password"
                placeholder="Digite a senha"
                value={senha}
                onChange={(e) => setSenha(e.target.value)}
                className="form-input"
                required
                autoComplete="new-password"
              />
            </div>
            <div className="form-group">
              <label>Tipo de Acesso</label>
              <select
                value={tipoAcesso}
                onChange={(e) => setTipoAcesso(e.target.value)}
                className="form-input"
                required
              >
                <option value="CAIXA">Caixa</option>
                <option value="ADMINISTRADOR">Administrador</option>
              </select>
            </div>
          </div>
          <div className="form-actions">
            <Button type="submit" className="btn-adicionar">Criar Acesso</Button>
          </div>
        </form>
        <ToastContainer position="top-right" autoClose={3000} />
      </div>
    </div>
  );
} 