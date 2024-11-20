import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export function Button({ 
  children, 
  type = 'button', 
  onClick, 
  variant = 'primary',
  fullWidth = false 
}) {
  const buttonClass = variant === 'primary' ? 'btn-custom' : `btn-${variant}`;

  return (
    <button 
      type={type} 
      onClick={onClick}
      className={`btn ${buttonClass} ${fullWidth ? 'w-100' : ''}`}
    >
      {children}
    </button>
  );
}