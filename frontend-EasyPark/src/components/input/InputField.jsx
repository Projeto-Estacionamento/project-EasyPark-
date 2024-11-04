import React from 'react';
import './InputField.css';

export function InputField({ label, type, name, value, onChange }) {
  return (
    <div className="input-field">
      <label>{label}</label>
      <input
        type={type}
        name={name}
        value={value}
        onChange={onChange}
      />
    </div>
  );
} 