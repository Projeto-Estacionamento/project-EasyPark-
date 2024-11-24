import api from './axiosConfig';

const API_URL = '/usuarios';

export const fetchClientes = async () => {
  const response = await api.get(API_URL);
  return response.data;
};

export const criarCliente = async (cliente) => {
  const response = await api.post(API_URL, cliente);
  return response.data;
};
 