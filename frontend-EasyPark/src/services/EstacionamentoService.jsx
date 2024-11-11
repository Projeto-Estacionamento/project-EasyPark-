import axios from 'axios';

const API_URL = 'http://localhost:8080/api/configuracoes';

// Configuração de instância do axios
const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Função para obter a configuração atual do sistema
export const getConfiguracaoAtual = async () => {
  try {
    const response = await api.get('/atual');
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar configuração:', error);
    throw error;
  }
};

// Função para atualizar a configuração do sistema
export const updateConfiguracao = async (configuracao) => {
  try {
    const response = await api.put(`/${configuracao.id}`, configuracao);
    return response.data;
  } catch (error) {
    console.error('Erro ao atualizar configuração:', error);
    throw error;
  }
};

// Outras funções de serviço podem ser adicionadas aqui 