import api from './axiosConfig';

const API_URL = '/usuarios';

export const fetchClientes = async () => {
  const token = sessionStorage.getItem('token');
  const response = await api.get(API_URL, {
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
  });
  return response.data;
};

export const criarCliente = async (cliente) => {
  const token = sessionStorage.getItem('token');
  const response = await api.post('http://localhost:8080/easypark/usuarios', cliente, {
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
  });
  return response.data;
};
 