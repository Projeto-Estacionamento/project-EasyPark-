import api from './axiosConfig';

const API_URL = 'http://localhost:8080/easypark/configuracoes';

export const getConfiguracaoAtual = async () => {
  const response = await api.get(`${API_URL}/atual`);
  return response.data;
};

export const updateConfiguracao = async (id, configuracao) => {
  const response = await api.put(`${API_URL}/${id}`, configuracao);
  return response.data;
};
