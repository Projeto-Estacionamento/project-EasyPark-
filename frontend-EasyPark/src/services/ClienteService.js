import axios from 'axios';

const API_URL = 'http://localhost:8080/easypark/usuarios';

export const fetchClientes = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar clientes:', error);
    throw error;
  }
};

export const criarCliente = async (cliente) => {
  try {
    const response = await axios.post(API_URL, cliente);
    return response.data;
  } catch (error) {
    console.error('Erro ao criar cliente:', error);
    throw error;
  }
};
 