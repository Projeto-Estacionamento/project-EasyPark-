import api from './axiosConfig';

export const fetchVeiculos = async () => {
  try {
    const response = await api.get('/veiculos');
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar veículos:', error);
    throw error;
  }
};

export const criarVeiculo = async (veiculo) => {
  try {
    const response = await api.post('/veiculos', veiculo);
    return response.data;
  } catch (error) {
    console.error('Erro ao criar veículo:', error);
    throw error;
  }
};
