import React, { useState, useEffect } from 'react';

export function RelatorioUsuariosClientes() {
  const [usuarios, setUsuarios] = useState([]);
  const [filtroPlano, setFiltroPlano] = useState('');

  useEffect(() => {
    // Fetch usuários/clientes do backend
    const fetchUsuarios = async () => {
      // Simulação de fetch
      const data = await fetch('/api/usuarios').then(res => res.json());
      setUsuarios(data);
    };

    fetchUsuarios();
  }, []);

  const usuariosFiltrados = usuarios.filter(usuario => 
    filtroPlano ? usuario.plano === filtroPlano : true
  );

  return (
    <div>
      <h2>Usuários/Clientes</h2>
      <select onChange={(e) => setFiltroPlano(e.target.value)} value={filtroPlano}>
        <option value="">Todos os Planos</option>
        <option value="basico">Básico</option>
        <option value="premium">Premium</option>
        {/* Adicione mais opções conforme necessário */}
      </select>
      <ul>
        {usuariosFiltrados.map((usuario, index) => (
          <li key={index}>{usuario.username} - {usuario.plano}</li>
        ))}
      </ul>
    </div>
  );
}