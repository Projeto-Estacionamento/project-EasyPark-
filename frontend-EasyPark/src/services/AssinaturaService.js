export const fetchAssinaturas = async () => {
  const response = await fetch('/api/assinaturas');
  if (!response.ok) {
    throw new Error('Erro ao buscar assinaturas');
  }
  return response.json();
};

export const criarAssinatura = async (assinatura) => {
  const response = await fetch('/api/assinaturas', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(assinatura),
  });
  if (!response.ok) {
    throw new Error('Erro ao criar assinatura');
  }
  return response.json();
};

export const atualizarStatusAssinatura = async (id, novoStatus) => {
  const response = await fetch(`/api/assinaturas/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ ativo: novoStatus }),
  });
  if (!response.ok) {
    throw new Error('Erro ao atualizar status da assinatura');
  }
  return response.json();
}; 