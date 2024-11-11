import React from 'react';
import './Card.css';

export function Card({ children, title }) {
  return (
    <div className="card">
      {title && <h1 className="card-title">{title}</h1>}
      {children}
    </div>
  );
} 