import api from './axiosConfig';

export const criarTicket = async (ticket) => {
  try {
    const response = await api.post('/tickets', ticket);
    return response.data;
  } catch (error) {
    console.error('Erro ao criar ticket:', error);
    throw error;
  }
};

export const listarTickets = async () => {
  try {
    const response = await api.get('/tickets');
    return response.data;
  } catch (error) {
    console.error('Erro ao listar tickets:', error);
    throw error;
  }
};

export const finalizarTicket = async (id) => {
  try {
    const response = await api.put(`/tickets/${id}/finalizar`);
    return response.data;
  } catch (error) {
    console.error('Erro ao finalizar ticket:', error);
    throw error;
  }
};
