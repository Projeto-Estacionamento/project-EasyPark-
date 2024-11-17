import React from 'react';

export function ListaAssinaturas({ assinaturas, alterarStatusAssinatura }) {
  return (
    <div>
      <h2>Lista de Assinaturas</h2>
      <ul>
        {assinaturas.map((assinatura) => (
          <li key={assinatura.id}>
            {assinatura.usuarioDTO.nome} - {assinatura.planoDTO.tipoPlano} - {assinatura.ativo ? 'Ativo' : 'Inativo'}
            <button onClick={() => alterarStatusAssinatura(assinatura.id, !assinatura.ativo)}>
              {assinatura.ativo ? 'Desativar' : 'Ativar'}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
} 