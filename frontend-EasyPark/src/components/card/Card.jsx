import React from 'react';
import './Card.css';

export function Card({ title, children }) {
  return (
    <div className="card">
      {title && <h2 className="card-title">{title}</h2>}
      <div className="card-content">
        {children}
      </div>
    </div>
  );
} 