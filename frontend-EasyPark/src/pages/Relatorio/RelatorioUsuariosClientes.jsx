import React, { useState, useEffect } from 'react';
import { RelatorioBase } from '../../components/RelatorioBase/RelatorioBase';

export function RelatorioUsuariosClientes() {
  const [usuarios, setUsuarios] = useState([]);
  const [filtroPlano, setFiltroPlano] = useState('');

  useEffect(() => {
    const fetchUsuarios = async () => {
      const data = await fetch('/api/usuarios').then(res => res.json());
      setUsuarios(data);
    };

    fetchUsuarios();
  }, []);

  const usuariosFiltrados = usuarios.filter(usuario => 
    filtroPlano ? usuario.plano === filtroPlano : true
  );

  return (
    <RelatorioBase title="Usuários/Clientes">
      <select onChange={(e) => setFiltroPlano(e.target.value)} value={filtroPlano} className="form-select mb-3">
        <option value="">Todos os Planos</option>
        <option value="basico">Básico</option>
        <option value="premium">Premium</option>
      </select>
      <ul className="list-group">
        {usuariosFiltrados.map((usuario, index) => (
          <li key={index} className="list-group-item border-bottom">{usuario.username} - {usuario.plano}</li>
        ))}
      </ul>
    </RelatorioBase>
  );
}