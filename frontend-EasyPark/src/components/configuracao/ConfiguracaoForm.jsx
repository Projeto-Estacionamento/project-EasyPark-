import React from 'react';
import { Input } from '../input/Input';
import { Button } from '../button/Button';

export function ConfiguracaoForm({ configuracao, handleChange, handleSubmit, isReadOnly }) {
  return (
    <form onSubmit={handleSubmit}>
      <Input
        label="Quantidade de Vagas para Motos:"
        type="number"
        name="qtdMoto"
        value={configuracao.qtdMoto}
        onChange={handleChange}
        readOnly={isReadOnly}
      />
      <Input
        label="Quantidade de Vagas para Carros:"
        type="number"
        name="qtdCarro"
        value={configuracao.qtdCarro}
        onChange={handleChange}
        readOnly={isReadOnly}
      />
      <Input
        label="Valor da Hora para Motos:"
        type="number"
        step="0.01"
        name="valorHoraMoto"
        value={configuracao.valorHoraMoto}
        onChange={handleChange}
        readOnly={isReadOnly}
      />
      <Input
        label="Valor da Hora para Carros:"
        type="number"
        step="0.01"
        name="valorHoraCarro"
        value={configuracao.valorHoraCarro}
        onChange={handleChange}
        readOnly={isReadOnly}
      />
      <Input
        label="Valor da Diária para Carros:"
        type="number"
        step="0.01"
        name="valorDiariaCarro"
        value={configuracao.valorDiariaCarro}
        onChange={handleChange}
        readOnly={isReadOnly}
      />
      <Input
        label="Valor da Diária para Motos:"
        type="number"
        step="0.01"
        name="valorDiariaMoto"
        value={configuracao.valorDiariaMoto}
        onChange={handleChange}
        readOnly={isReadOnly}
      />
      <Input
        label="Hora Máxima Avulso:"
        type="number"
        step="0.01"
        name="horaMaximaAvulso"
        value={configuracao.horaMaximaAvulso}
        onChange={handleChange}
        readOnly={isReadOnly}
      />
      {!isReadOnly && <Button type="submit">Salvar Configurações</Button>}
    </form>
  );
} 