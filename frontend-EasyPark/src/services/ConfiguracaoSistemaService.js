import api from './axiosConfig';

export const getConfiguracaoAtual = async () => {
  const token = sessionStorage.getItem('token');
  try {
    const response = await api.get(`/configuracoes/atual`, {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar configuração:', error);
    throw error;
  }
};

export const updateConfiguracao = async (configuracao) => {
  const token = sessionStorage.getItem('token');
  try {
    const response = await api.put(`/configuracoes/1`, {
      "id": 1,
      "mostrar": false,
      "qtdMoto": configuracao.qtdMoto,
      "qtdCarro": configuracao.qtdCarro,
      "valorHoraMoto": configuracao.valorHoraMoto,
      "valorHoraCarro": configuracao.valorHoraCarro,
      "valorDiariaCarro": configuracao.valorDiariaCarro,
      "valorDiariaMoto": configuracao.valorDiariaMoto,
      "horaMaximaAvulso": configuracao.horaMaximaAvulso
    }, {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao atualizar configuração:', error.response?.data || error);
    throw error;
  }
};
