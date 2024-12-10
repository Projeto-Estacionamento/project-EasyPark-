import api from './axiosConfig';

export const fetchAssinaturas = async () => {
  try {
    const token = sessionStorage.getItem('token');
    const response = await api.get('/assinaturas', {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar assinaturas:', error);
    throw error;
  }
};

export const criarAssinatura = async (assinatura) => {
  try {
    const token = sessionStorage.getItem('token');
    const response = await api.post('/assinaturas', assinatura, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao criar assinatura:', error);
    throw error;
  }
};

export const atualizarStatusAssinatura = async (id, novoStatus) => {
  const token = sessionStorage.getItem('token');
  try {
    const response = await api.put(`/assinaturas/${id}`, 
      { 
        id: id,
        ativo: novoStatus 
      },
      {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        }
      }
    );
    return response.data;
  } catch (error) {
    console.error('Erro ao atualizar status da assinatura:', error);
    throw error;
  }
};

export const fetchPlanos = async () => {
  const token = sessionStorage.getItem('token');
  try {
    const response = await api.get('/planos', {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar planos:', error);
    throw error;
  }
};

export const fetchUsuarios = async () => {
  const token = sessionStorage.getItem('token');
  try {
    const response = await api.get('/usuarios', {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      }
    });
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar usuários:', error);
    throw error;
  }
};

export const fetchUsuarioById = async (id) => {
  const token = sessionStorage.getItem('token');
  try {
    const response = await api.get(`/usuarios/${id}`, {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      },
    });
    console.log('Dados do usuário:', response.data); // Para debug
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar usuário:', error);
    throw error;
  }
}; 