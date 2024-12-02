import React, { useState, useEffect } from 'react';
import { ConfiguracaoForm } from '../../components/configuracao/ConfiguracaoForm';
import { getConfiguracaoAtual, updateConfiguracao } from '../../services/ConfiguracaoSistemaService';
import { SidebarMenu } from '../../components/sidebarMenu/SidebarMenu';
import { Button } from '../../components/button/button';

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
            <h2 className="header-title">Configuração do Estacionamento</h2>
            <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Quantidade de Vagas (Moto)</label>
                        <input type="number" name="qtdMoto" value={configuracao.qtdMoto} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Quantidade de Vagas (Carro)</label>
                        <input type="number" name="qtdCarro" value={configuracao.qtdCarro} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Valor Hora (Moto)</label>
                        <input type="number" name="valorHoraMoto" value={configuracao.valorHoraMoto} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Valor Hora (Carro)</label>
                        <input type="number" name="valorHoraCarro" value={configuracao.valorHoraCarro} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Valor Diário (Moto)</label>
                        <input type="number" name="valorDiariaMoto" value={configuracao.valorDiariaMoto} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Valor Diário (Carro)</label>
                        <input type="number" name="valorDiariaCarro" value={configuracao.valorDiariaCarro} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Hora Máxima Avulso</label>
                        <input type="number" name="horaMaximaAvulso" value={configuracao.horaMaximaAvulso} onChange={handleChange} />
                    </div>
                    <Button type="submit" fullWidth>Salvar</Button>
                </form>
            </div>
        </div>
    );
}