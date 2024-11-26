import api from './axiosConfig';

export const listarTicketsFechados = async () => {
  try {
    const token = sessionStorage.getItem('token');
    const response = await api.get('/relatorios/tickets-fechados', {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao listar tickets fechados:', error);
    throw error;
  }
};