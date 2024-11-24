import React, { useState } from 'react';
import FetchWithAuth from '../../components/FetchWithAuth'; // Ajuste o caminho conforme necessário

function ListaUsuarios() {
  const [tipoAcesso, setTipoAcesso] = useState('ADMINISTRADOR'); // Valor padrão
  const [url, setUrl] = useState('http://localhost:8080/easypark/acesso');

  const handleFilter = () => {
    setUrl(`http://localhost:8080/easypark/acesso/tipo?tipo=${tipoAcesso}`);
  };

  const handleRemoveFilter = () => {
    setUrl('http://localhost:8080/easypark/acesso');
  };

  return (
    <div>
      <h2>Lista de Usuários</h2>
      <div>
        <label htmlFor="tipoAcesso">Tipo de Acesso: </label>
        <select
          id="tipoAcesso"
          value={tipoAcesso}
          onChange={(e) => setTipoAcesso(e.target.value)}
          style={{ marginRight: '10px' }}
        >
          <option value="ADMINISTRADOR">Administrador</option>
          <option value="CAIXA">Caixa</option>
        </select>
        <button onClick={handleFilter} style={{ marginRight: '10px' }}>Filtrar</button>
        <button onClick={handleRemoveFilter}>Remover Filtro</button>
      </div>
      <FetchWithAuth
        url={url}
        render={data => (
          <ul>
            {data.map(usuario => (
              <li key={usuario.id}>
                {usuario.email} - {usuario.tipoAcesso}
              </li>
            ))}
          </ul>
        )}
      />
    </div>
  );
}

export default ListaUsuarios;