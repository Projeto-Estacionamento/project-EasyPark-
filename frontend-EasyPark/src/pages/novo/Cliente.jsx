import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { FiArrowLeft, FiSave } from 'react-icons/fi';
import { Button } from '../../components/button/button';
import { Card } from '../../components/card/Card';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import './cliente.css';
import { criarCliente } from '../../services/ClienteService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export function Cliente() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [formData, setFormData] = useState({
    nome: '',
    email: '',
    telefone: '',
    cpf: '',
    cidade: '',
    estado: '',
    cep: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'cep') {
      const cepValue = value.replace(/\D/g, '');
      if (cepValue.length === 8) {
        buscarCep(cepValue);
      }
    }
    
    setFormData(prev => ({
      ...prev,
      [name]: name === 'cpf' ? formatarCPF(value) : 
              name === 'cep' ? formatarCEP(value) : value
    }));
  };

  const buscarCep = async (cep) => {
    try {
      setLoading(true);
      const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
      const data = await response.json();
      
      if (!data.erro) {
        setFormData(prev => ({
          ...prev,
          cidade: data.localidade,
          estado: data.uf
        }));
      }
    } catch (error) {
      console.error('Erro ao buscar CEP:', error);
    } finally {
      setLoading(false);
    }
  };

  const formatarCEP = (cep) => {
    return cep
      .replace(/\D/g, '')
      .replace(/(\d{5})(\d)/, '$1-$2')
      .substr(0, 9);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      await criarCliente(formData);
      toast.success('Cliente criado com sucesso!');
      navigate('/gerenciamento-cliente');
    } catch (error) {
      console.error('Erro ao criar cliente:', error);
      toast.error('Erro ao criar cliente. Por favor, tente novamente.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="novo-cliente-container">
          <div className="novo-cliente-header">
            <Button 
              className="btn-voltar"
              onClick={() => navigate('/gerenciamento-cliente')}
            >
              <FiArrowLeft style={{ marginRight: '0.5rem' }} size={20} />
              Voltar
            </Button>
            <h2 className="novo-cliente-title">Novo Cliente</h2>
          </div>

          <form onSubmit={handleSubmit} className="novo-cliente-form">
            <div className="form-grid">
              <div className="form-group">
                <label>Nome</label>
                <input
                  type="text"
                  name="nome"
                  value={formData.nome}
                  onChange={handleChange}
                  className="form-input"
                  required
                />
              </div>
              <div className="form-group">
                <label>Email</label>
                <input
                  type="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  className="form-input"
                  required
                />
              </div>
              <div className="form-group">
                <label>Telefone</label>
                <input
                  type="text"
                  name="telefone"
                  value={formData.telefone}
                  onChange={handleChange}
                  className="form-input"
                  required
                />
              </div>
              <div className="form-group">
                <label>CPF</label>
                <input
                  type="text"
                  name="cpf"
                  value={formData.cpf}
                  onChange={handleChange}
                  className="form-input"
                  required
                />
              </div>
              <div className="form-group">
                <label>CEP</label>
                <input
                  type="text"
                  name="cep"
                  value={formData.cep}
                  onChange={handleChange}
                  className={`form-input ${loading ? 'loading' : ''}`}
                  required
                  maxLength="9"
                  placeholder="00000-000"
                />
                {loading && <span className="loading-indicator"></span>}
              </div>
              <div className="form-group">
                <label>Cidade</label>
                <input
                  type="text"
                  name="cidade"
                  value={formData.cidade}
                  onChange={handleChange}
                  className="form-input"
                  required
                  readOnly
                />
              </div>
              <div className="form-group">
                <label>Estado</label>
                <input
                  type="text"
                  name="estado"
                  value={formData.estado}
                  onChange={handleChange}
                  className="form-input"
                  required
                  readOnly
                />
              </div>
            </div>

            <div className="form-actions">
              <Button 
                type="submit" 
                className="btn-salvar"
              >
                <FiSave style={{ marginRight: '0.5rem' }} size={20} />
                Salvar
              </Button>
            </div>
          </form>
      </div>
      <ToastContainer position="top-right" autoClose={3000} />
    </div>
  );
}

function formatarCPF(cpf) {
  return cpf
    .replace(/\D/g, '') // Remove caracteres não numéricos
    .replace(/(\d{3})(\d)/, '$1.$2') // Adiciona o primeiro ponto
    .replace(/(\d{3})(\d)/, '$1.$2') // Adiciona o segundo ponto
    .replace(/(\d{3})(\d{1,2})$/, '$1-$2'); // Adiciona o traço
}
// export const criarCliente = async (cliente) => {
//   const token = sessionStorage.getItem('token');
//   const response = await api.post(API_URL, cliente, {
//     headers: {
//       'Authorization': `Bearer ${token}`,
//       'Content-Type': 'application/json'
//     }
//   });
//   return response.data;
// }; 