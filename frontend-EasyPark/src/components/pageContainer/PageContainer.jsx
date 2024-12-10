import React from 'react';

export function PageContainer({ children, darkMode = false }) {
  return (
    <div
      className={`d-flex flex-column justify-content-center align-items-center min-vh-100 ${darkMode ? 'text-white' : ''}`}
      style={{
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat',
      }}
    >
      {children}
    </div>
  );
} 