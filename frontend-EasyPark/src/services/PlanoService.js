export const fetchPlanos = async () => {
  const token = sessionStorage.getItem('token');
  const response = await fetch('http://localhost:8080/easypark/planos', {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error('Erro ao buscar planos');
  }
  return response.json();
};

export const criarPlano = async (plano) => {
  const token = sessionStorage.getItem('token');
  const response = await fetch('http://localhost:8080/easypark/planos', {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(plano),
  });
  if (!response.ok) {
    throw new Error('Erro ao criar plano');
  }
  return response.json();
}; 