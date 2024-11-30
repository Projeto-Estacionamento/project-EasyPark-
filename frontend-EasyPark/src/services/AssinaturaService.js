import api from './axiosConfig';

export const fetchAssinaturas = async () => {
  try {
    const token = sessionStorage.getItem('token');
    const response = await api.get('/assinaturas', {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar assinaturas:', error);
    throw error;
  }
};

export const criarAssinatura = async (assinatura) => {
  try {
    const token = sessionStorage.getItem('token');
    const response = await api.post('/assinaturas', assinatura, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao criar assinatura:', error);
    throw error;
  }
};

export const atualizarStatusAssinatura = async (id, novoStatus) => {
  try {
    const token = sessionStorage.getItem('token');
    const response = await api.put(`/assinaturas/${id}`, { ativo: novoStatus }, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao atualizar status da assinatura:', error);
    throw error;
  }
};

export const fetchPlanos = async () => {
  try {
    const response = await api.get('/planos');
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar planos:', error);
    throw error;
  }
};

export const fetchUsuarios = async () => {
  try {
    const response = await api.get('/usuarios');
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar usuários:', error);
    throw error;
  }
}; 