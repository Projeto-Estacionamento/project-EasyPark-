import React from 'react';
import { InputField } from '../input/InputField';
import { Button } from '../button/Button';

export function ConfiguracaoForm({ configuracao, handleChange, handleSubmit }) {
  return (
    <form onSubmit={handleSubmit}>
      <InputField
        label="Quantidade de Vagas para Motos:"
        type="number"
        name="qtdMoto"
        value={configuracao.qtdMoto}
        onChange={handleChange}
      />
      <InputField
        label="Quantidade de Vagas para Carros:"
        type="number"
        name="qtdCarro"
        value={configuracao.qtdCarro}
        onChange={handleChange}
      />
      <InputField
        label="Valor por Hora para Motos:"
        type="number"
        step="0.01"
        name="valorHoraMoto"
        value={configuracao.valorHoraMoto}
        onChange={handleChange}
      />
      <InputField
        label="Valor por Hora para Carros:"
        type="number"
        step="0.01"
        name="valorHoraCarro"
        value={configuracao.valorHoraCarro}
        onChange={handleChange}
      />
      <InputField
        label="Valor da Diária para Carros:"
        type="number"
        step="0.01"
        name="valorDiariaCarro"
        value={configuracao.valorDiariaCarro}
        onChange={handleChange}
      />
      <InputField
        label="Valor da Diária para Motos:"
        type="number"
        step="0.01"
        name="valorDiariaMoto"
        value={configuracao.valorDiariaMoto}
        onChange={handleChange}
      />
      <InputField
        label="Hora Máxima Avulso:"
        type="number"
        step="0.01"
        name="horaMaximaAvulso"
        value={configuracao.horaMaximaAvulso}
        onChange={handleChange}
      />
      <Button type="submit">Salvar Configurações</Button>
    </form>
  );
} 