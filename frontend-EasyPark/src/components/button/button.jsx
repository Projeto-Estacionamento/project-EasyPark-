import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

export function Button({ 
  children, 
  type = 'button', 
  onClick, 
  variant = 'primary',
  fullWidth = false
}) {
  const navigate = useNavigate();
  const buttonClass = variant === 'primary' ? 'btn-custom' : `btn-${variant}`;

  const handleClick = (e) => {
    if (onClick) {
      onClick(e);
    }
  };

  return (
    <button 
      type={type} 
      onClick={handleClick}
      className={`btn ${buttonClass} ${fullWidth ? 'w-100' : ''}`}
      style={{ width: '150px', height: '50px' }}
    >
      {children}
    </button>
  );
}