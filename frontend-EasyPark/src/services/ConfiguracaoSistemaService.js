import api from './axiosConfig';

const API_URL = 'http://localhost:8080/easypark/configuracoes';

export const getConfiguracaoAtual = async () => {
  const token = sessionStorage.getItem('token');
  const response = await api.get(`${API_URL}/atual`, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  return response.data;
};

export const updateConfiguracao = async (id, configuracao) => {
  const token = sessionStorage.getItem('token');
  const response = await api.put(`${API_URL}/${id}`, configuracao, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  return response.data;
};
