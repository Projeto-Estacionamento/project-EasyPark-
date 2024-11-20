import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export function Card({ title, children }) {
  return (
    <div className="card text-center my-3" style={{ backgroundColor: '#00838f', border: '2px solid white' }}>
      <div className="card-body">
        {title && <h2 className="card-title">{title}</h2>}
        {children}
      </div>
    </div>
  );
} 