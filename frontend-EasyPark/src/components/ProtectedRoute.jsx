import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';

export function ProtectedRoute({ children, allowedRoles }) {
  const { user } = useAuth();

  if (!user || !allowedRoles.includes(user.tipoAcesso)) {
    // Redireciona para a página de login se o usuário não estiver autenticado ou não tiver permissão
    return <Navigate to="/" />;
  }

  return children;
} 