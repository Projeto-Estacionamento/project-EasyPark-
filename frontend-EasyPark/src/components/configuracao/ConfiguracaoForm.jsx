import React from 'react';
import './ConfiguracaoForm.css';

export function ConfiguracaoForm({ configuracao, handleChange, handleSubmit, isReadOnly }) {

  return (
    <>
      <div className="header">
        <h2>Configuração do Estacionamento</h2>
      </div>

      <form onSubmit={handleSubmit} className="config-form">
        <div className="input-row">
          <div className="input-group">
            <label>Quantidade de Vagas (Moto)</label>
            <input
              type="number"
              name="qtdMoto"
              value={configuracao.qtdMoto}
              onChange={handleChange}
              readOnly={isReadOnly}
            />
          </div>

          <div className="input-group">
            <label>Quantidade de Vagas (Carro)</label>
            <input
              type="number"
              name="qtdCarro"
              value={configuracao.qtdCarro}
              onChange={handleChange}
              readOnly={isReadOnly}
            />
          </div>
        </div>

        <div className="input-row">
          <div className="input-group">
            <label>Valor Hora (Moto)</label>
            <input
              type="number"
              name="valorHoraMoto"
              value={configuracao.valorHoraMoto}
              onChange={handleChange}
              readOnly={isReadOnly}
            />
          </div>

          <div className="input-group">
            <label>Valor Hora (Carro)</label>
            <input
              type="number"
              name="valorHoraCarro"
              value={configuracao.valorHoraCarro}
              onChange={handleChange}
              readOnly={isReadOnly}
            />
          </div>
        </div>

        <div className="input-row">
          <div className="input-group">
            <label>Valor Diária (Moto)</label>
            <input
              type="number"
              name="valorDiariaMoto"
              value={configuracao.valorDiariaMoto}
              onChange={handleChange}
              readOnly={isReadOnly}
            />
          </div>

          <div className="input-group">
            <label>Valor Diária (Carro)</label>
            <input
              type="number"
              name="valorDiariaCarro"
              value={configuracao.valorDiariaCarro}
              onChange={handleChange}
              readOnly={isReadOnly}
            />
          </div>
        </div>

        <div className="input-row">
          <div className="input-group">
            <label>Hora Máxima Avulso</label>
            <input
              type="number"
              name="horaMaximaAvulso"
              value={configuracao.horaMaximaAvulso}
              onChange={handleChange}
              readOnly={isReadOnly}
            />
          </div>
        </div>

        {!isReadOnly && (
          <div className="form-actions">
            <button type="submit" className="salvar-btn">
              <span className="icon">💾</span>
              Salvar
            </button>
          </div>
        )}
      </form>
    </>
  );
} 