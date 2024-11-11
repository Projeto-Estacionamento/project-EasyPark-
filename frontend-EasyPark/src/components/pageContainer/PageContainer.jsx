import React from 'react';
import './PageContainer.css';

export function PageContainer({ children, darkMode = false }) {
  return (
    <div className={`page-container ${darkMode ? 'dark' : ''}`}>
      {children}
    </div>
  );
} 