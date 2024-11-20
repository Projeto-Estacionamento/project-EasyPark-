import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export function PageContainer({ children, darkMode = false }) {
  return (
    <div className={`d-flex flex-column justify-content-center align-items-center min-vh-100 ${darkMode ? 'bg-dark text-white' : ''}`}>
      {children}
    </div>
  );
} 