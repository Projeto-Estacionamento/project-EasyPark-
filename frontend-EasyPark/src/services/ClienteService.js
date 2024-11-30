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
  console.log('Dados enviados:', cliente);
  console.log('Token:', token);
  
  const dadosFormatados = {
    nome: cliente.nome,
    email: cliente.email,
    telefone: cliente.telefone,
    cpf: cliente.cpf,
    endereco: {
      cidade: cliente.cidade,
      estado: cliente.estado,
      cep: cliente.cep
    }
  };
  
  const response = await api.post('http://localhost:8080/easypark/usuarios', dadosFormatados, {
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
  });
  return response.data;
};
 