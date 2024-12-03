import React, { useContext, useEffect } from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

function ProtectedRoute({ children }) {
  const { isAuthenticated } = useContext(AuthContext);
  const location = useLocation();

  useEffect(() => {
    const token = sessionStorage.getItem('token');
    const accessType = sessionStorage.getItem('accessType');

    if (!token || !accessType) {
      // Limpa todo o sessionStorage
      sessionStorage.clear();
      // Redireciona para a página de login
      window.location.href = '/';
    }
  }, [isAuthenticated]);

  if (!isAuthenticated) {
    return <Navigate to="/" replace state={{ from: location }} />;
  }

  return children;
}

export default ProtectedRoute; 