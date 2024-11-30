import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export function Container({ children }) {
  return <div className="container d-flex flex-column justify-content-center align-items-center min-vh-100">{children}</div>;
} 