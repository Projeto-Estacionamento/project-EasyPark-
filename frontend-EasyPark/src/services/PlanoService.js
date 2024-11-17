export const fetchPlanos = async () => {
  const response = await fetch('/api/planos');
  if (!response.ok) {
    throw new Error('Erro ao buscar planos');
  }
  return response.json();
};

export const criarPlano = async (plano) => {
  const response = await fetch('/api/planos', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(plano),
  });
  if (!response.ok) {
    throw new Error('Erro ao criar plano');
  }
  return response.json();
}; 