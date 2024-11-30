import React, { useState, useEffect } from 'react';
import { ConfiguracaoForm } from '../../components/configuracao/ConfiguracaoForm';
import { getConfiguracaoAtual, updateConfiguracao } from '../../services/ConfiguracaoSistemaService';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';

export function ConfiguracaoEstacionamentoAdmin() {
  const [configuracao, setConfiguracao] = useState({
    id: 1,
    mostrar: false,
    qtdMoto: 0,
    qtdCarro: 0,
    valorHoraMoto: 0.0,
    valorHoraCarro: 0.0,
    valorDiariaCarro: 0.0,
    valorDiariaMoto: 0.0,
    horaMaximaAvulso: 0.0,
  });


  useEffect(() => {
    const fetchConfiguracao = async () => {
      try {
        const data = await getConfiguracaoAtual();
        setConfiguracao(data);
      } catch (error) {
        console.error('Erro ao buscar configuração:', error);
      }
    };

    fetchConfiguracao();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setConfiguracao({ ...configuracao, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await updateConfiguracao(configuracao);
      alert('Configuração atualizada com sucesso!');
    } catch (error) {
      console.error('Erro ao atualizar configuração:', error);
    }
  };

  const tipoUsuario = sessionStorage.getItem('accessType');

  return (
    <div className="d-flex">
      <SidebarMenu />
      <div className="configuracao-container">
          <ConfiguracaoForm
            configuracao={configuracao}
            handleChange={handleChange}
            handleSubmit={tipoUsuario === 'ADMINISTRADOR' ? handleSubmit : null}
            isReadOnly={tipoUsuario !== 'ADMINISTRADOR'}
          />
      </div>
    </div>
  );
}