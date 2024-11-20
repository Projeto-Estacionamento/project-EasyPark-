import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

export function BackgroundWrapper({ children }) {
  return <div className="d-flex justify-content-center align-items-center min-vh-100">{children}</div>;
}