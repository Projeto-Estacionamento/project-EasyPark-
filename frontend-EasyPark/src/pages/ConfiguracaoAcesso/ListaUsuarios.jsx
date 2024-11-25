import React, { useState } from 'react';
import { Button } from '../../components/button/button';
import FetchWithAuth from '../../components/FetchWithAuth';

function ListaUsuarios() {
  const [tipoAcesso, setTipoAcesso] = useState('ADMINISTRADOR');
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
        <Button onClick={handleFilter} variant="outline-light" style={{ marginRight: '10px' }}>
          Filtrar
        </Button>
        <Button onClick={handleRemoveFilter} variant="outline-light" style={{ marginLeft: '10px' }}>
          Remover Filtro
        </Button>
      </div>
      <FetchWithAuth
        url={url}
        render={data => (
          <ul style={{ listStyleType: 'none', padding: 0 }}>
            {data.map(usuario => (
              <li key={usuario.id} style={{ marginBottom: '10px', wordBreak: 'break-word' }}>
                <strong>{usuario.tipoAcesso}</strong> - {usuario.email}
              </li>
            ))}
          </ul>
        )}
      />
    </div>
  );
}

export default ListaUsuarios;