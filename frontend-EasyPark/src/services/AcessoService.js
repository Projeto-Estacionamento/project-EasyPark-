import api from './axiosConfig';

export const listarAcessos = async () => {
  const token = sessionStorage.getItem('token');
  try {
    const response = await api.get('/acesso', {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao listar acessos:', error);
    throw error;
  }
};

export const listarAcessosPorTipo = async (tipo) => {
  const token = sessionStorage.getItem('token');
  try {
    const response = await api.get(`/acesso/tipo?tipo=${tipo}`, {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao listar acessos por tipo:', error);
    throw error;
  }
};

export const registrarAcesso = async (dados) => {
  const token = sessionStorage.getItem('token');
  try {
    const response = await api.post('/auth/register', {
      email: dados.email,
      senha: dados.senha,
      nome: dados.nome,
      tipoAcesso: dados.tipoAcesso
    }, {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao registrar acesso:', error);
    throw error;
  }
};

export const atualizarAcesso = async (id, dados) => {
  const token = sessionStorage.getItem('token');
  try {
    const dadosParaEnviar = { ...dados };
    if (!dadosParaEnviar.senha) {
      delete dadosParaEnviar.senha;
    }

    const response = await api.put(`/acesso/${id}`, dadosParaEnviar, {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao atualizar acesso:', error);
    throw error;
  }
};

export const buscarAcessoPorId = async (id) => {
  const token = sessionStorage.getItem('token');
  try {
    const response = await api.get(`/acesso/${id}`, {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar acesso:', error);
    throw error;
  }
};