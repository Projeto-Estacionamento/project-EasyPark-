import React from 'react';
import { Button } from '../../components/button/button';

export function ListaAssinaturas({ assinaturas, alterarStatusAssinatura }) {
  return (
    <div>
      <ul>
        {assinaturas.map((assinatura) => (
          <li key={assinatura.id} style={{ marginBottom: '10px' }}>
            {assinatura.usuarioDTO.nome} - {assinatura.planoDTO.tipoPlano} - {assinatura.ativo ? 'Ativo' : 'Inativo'}
            <Button
              onClick={() => alterarStatusAssinatura(assinatura.id, !assinatura.ativo)}
              variant="outline-light"
              style={{ marginLeft: '15px' }}
            >
              {assinatura.ativo ? 'Desativar' : 'Ativar'}
            </Button>
          </li>
        ))}
      </ul>
    </div>
  );
} 