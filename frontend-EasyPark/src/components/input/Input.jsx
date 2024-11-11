import React from 'react';
import './Input.css';

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
    <div className={`input-container ${darkMode ? 'dark' : ''}`}>
      <label htmlFor={name}>{text}</label>
      <input
        type={type}
        name={name}
        id={name}
        placeholder={placeholder}
        value={value}
        onChange={onChangeFN}
      />
    </div>
  );
} 