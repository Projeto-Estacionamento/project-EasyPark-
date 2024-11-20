import React, { useState, useEffect } from 'react';

export function ConfiguracaoAcesso() {
  const [usuarios, setUsuarios] = useState([]);
  const [mostrarNovoAcesso, setMostrarNovoAcesso] = useState(false);
  const [username, setUsername] = useState('');
  const [senha, setSenha] = useState('');
  const [tipoAcesso, setTipoAcesso] = useState('caixa');

  useEffect(() => {
    fetch('http://localhost:8080/acesso/listar')
      .then(response => response.json())
      .then(data => setUsuarios(data))
      .catch(error => console.error('Erro ao buscar usuários:', error));
  }, []);

  const adicionarUsuario = (novoUsuario) => {
    setUsuarios([...usuarios, novoUsuario]);
  };

  const editarSenhaUsuario = (index, novaSenha) => {
    const usuariosAtualizados = [...usuarios];
    usuariosAtualizados[index].senha = novaSenha;
    setUsuarios(usuariosAtualizados);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    adicionarUsuario({ username, senha, tipoAcesso });
    setUsername('');
    setSenha('');
    setMostrarNovoAcesso(false);
  };

  return (
    <div className="configuracao-acesso">
      <div className="card">
        <h1>Configuração de Acesso</h1>
        <button onClick={() => setMostrarNovoAcesso(!mostrarNovoAcesso)}>
          {mostrarNovoAcesso ? 'Cancelar' : 'Criar Novo Acesso'}
        </button>
        {mostrarNovoAcesso && (
          <div className="novo-acesso-card">
            <form onSubmit={handleSubmit}>
              <input
                type="text"
                placeholder="Usuário"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
              <input
                type="password"
                placeholder="Senha"
                value={senha}
                onChange={(e) => setSenha(e.target.value)}
              />
              <select value={tipoAcesso} onChange={(e) => setTipoAcesso(e.target.value)}>
                <option value="caixa">Caixa</option>
                <option value="administrador">Administrador</option>
              </select>
              <button type="submit">Criar Novo Acesso</button>
            </form>
          </div>
        )}
        <h2>Lista de Usuários</h2>
        <ul>
          {usuarios.map((usuario, index) => (
            <li key={index}>
              <span>{usuario.username} - {usuario.tipoAcesso}</span>
              <button onClick={() => {
                const novaSenha = prompt('Digite a nova senha:');
                if (novaSenha) {
                  editarSenhaUsuario(index, novaSenha);
                }
              }}>
                Editar Senha
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}