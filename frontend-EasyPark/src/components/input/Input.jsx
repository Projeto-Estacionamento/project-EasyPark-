import React from 'react';
import './Input.css';

export function Input({ type, text, name, placeholder, value, onChangeFN }) {
  return (
    <div className="input-container">
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