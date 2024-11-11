import React from 'react';
import './Button.css';

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
      className={`button ${variant} ${fullWidth ? 'full-width' : ''}`}
    >
      {children}
    </button>
  );
}