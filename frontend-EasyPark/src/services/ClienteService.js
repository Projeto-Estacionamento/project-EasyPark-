export const fetchClientes = async () => {
  const response = await fetch('/api/usuarios');
  if (!response.ok) {
    throw new Error('Erro ao buscar clientes');
  }
  return response.json();
};

export const criarCliente = async (cliente) => {
  const response = await fetch('/api/usuarios', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(cliente),
  });
  if (!response.ok) {
    throw new Error('Erro ao criar cliente');
  }
  return response.json();
}; 