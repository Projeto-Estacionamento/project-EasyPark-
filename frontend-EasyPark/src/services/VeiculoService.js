import api from './axiosConfig';

export const fetchVeiculos = async () => {
  const token = sessionStorage.getItem('token');
  try {
    const response = await api.get('/veiculos', {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      },
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar veículos:', error);
    throw error;
  }
};

export const criarVeiculo = async (veiculo) => {
  const token = sessionStorage.getItem('token');
  console.log('Dados enviados:', veiculo);
  console.log('Token:', token);
  
  try {
    const response = await api.post('http://localhost:8080/easypark/veiculos', {
      placa: veiculo.placa,
      tipoVeiculo: veiculo.tipoVeiculo,
      ocupandoVaga: veiculo.ocupandoVaga,
      idUsuarioDTO: veiculo.idUsuarioDTO,
      fabricanteDTO: {
        modelo: veiculo.fabricanteDTO.modelo,
        marca: veiculo.fabricanteDTO.marca,
        ano: veiculo.fabricanteDTO.ano
      }
    }, {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      },
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao criar veículo:', error);
    if (error.response) {
      console.log('Resposta do servidor:', error.response.data);
    }
    throw error;
  }
};
