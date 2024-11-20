import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export function Button({ 
  children, 
  type = 'button', 
  onClick, 
  variant = 'primary',
  fullWidth = false 
}) {
  return (
    <button 
      type={type} 
      onClick={onClick}
      className={`btn btn-${variant} ${fullWidth ? 'w-100' : ''}`}
    >
      {children}
    </button>
  );
}