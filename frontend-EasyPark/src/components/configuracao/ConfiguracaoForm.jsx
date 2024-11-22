import React from 'react';
import './ConfiguracaoForm.css';

export function ConfiguracaoForm({ configuracao, handleChange, handleSubmit, isReadOnly }) {
  return (
    <form onSubmit={handleSubmit} className="form-configuracao">
      <div className="input-group-config-estacionamento">
        <label>Quantidade de Motos:</label>
        <input
          type="number"
          name="qtdMoto"
          value={configuracao.qtdMoto}
          onChange={handleChange}
          readOnly={isReadOnly}
        />
      </div>
      <div className="input-group-config-estacionamento">
        <label>Quantidade de Carros:</label>
        <input
          type="number"
          name="qtdCarro"
          value={configuracao.qtdCarro}
          onChange={handleChange}
          readOnly={isReadOnly}
        />
      </div>
      <div className="input-group-config-estacionamento">
        <label>Valor por Hora para Motos:</label>
        <input
          type="number"
          name="valorHoraMoto"
          value={configuracao.valorHoraMoto}
          onChange={handleChange}
          readOnly={isReadOnly}
          step="0.01"
        />
      </div>
      <div className="input-group-config-estacionamento">
        <label>Valor por Hora para Carros:</label>
        <input
          type="number"
          name="valorHoraCarro"
          value={configuracao.valorHoraCarro}
          onChange={handleChange}
          readOnly={isReadOnly}
          step="0.01"
        />
      </div>
      <div className="input-group-config-estacionamento">
        <label>Valor da Diária para Carros:</label>
        <input
          type="number"
          name="valorDiariaCarro"
          value={configuracao.valorDiariaCarro}
          onChange={handleChange}
          readOnly={isReadOnly}
          step="0.01"
        />
      </div>
      <div className="input-group-config-estacionamento">
        <label>Valor da Diária para Motos:</label>
        <input
          type="number"
          name="valorDiariaMoto"
          value={configuracao.valorDiariaMoto}
          onChange={handleChange}
          readOnly={isReadOnly}
          step="0.01"
        />
      </div>
      <div className="input-group-config-estacionamento">
        <label>Hora Máxima Avulso:</label>
        <input
          type="number"
          name="horaMaximaAvulso"
          value={configuracao.horaMaximaAvulso}
          onChange={handleChange}
          readOnly={isReadOnly}
          step="0.01"
        />
      </div>
      {!isReadOnly && (
        <button type="submit">Salvar Configuração</button>
      )}
    </form>
  );
} 