import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export function Input({ 
  type, 
  text, 
  name, 
  placeholder, 
  value, 
  onChangeFN,
  darkMode = false 
}) {
  return (
    <div className={`mb-3 ${darkMode ? 'text-white' : ''}`}>
      <label htmlFor={name} className="form-label">{text}</label>
      <input
        type={type}
        name={name}
        id={name}
        placeholder={placeholder}
        value={value}
        onChange={onChangeFN}
        className={`form-control ${darkMode ? 'bg-dark text-white' : ''}`}
      />
    </div>
  );
} 