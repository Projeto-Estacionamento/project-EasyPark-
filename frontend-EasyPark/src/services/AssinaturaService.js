import api from './axiosConfig';

export const fetchAssinaturas = async () => {
  try {
    const response = await api.get('/assinaturas');
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar assinaturas:', error);
    throw error;
  }
};

export const criarAssinatura = async (assinatura) => {
  try {
    const response = await api.post('/assinaturas', assinatura);
    return response.data;
  } catch (error) {
    console.error('Erro ao criar assinatura:', error);
    throw error;
  }
};

export const atualizarStatusAssinatura = async (id, novoStatus) => {
  try {
    const response = await api.put(`/assinaturas/${id}`, { ativo: novoStatus });
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