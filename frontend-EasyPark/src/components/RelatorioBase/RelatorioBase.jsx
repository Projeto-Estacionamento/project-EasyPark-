import React from 'react';

export function RelatorioBase({ title, children }) {
  return (
    <div>
      <h2 className="text-white mb-4">{title}</h2>
      {children}
    </div>
  );
}
