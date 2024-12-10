import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';

export default function useAuth() {
  const { user, login, logout } = useContext(AuthContext);

  const loginUser = async (credentials) => {
    try {
      const response = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(credentials),
      });
      const data = await response.json();
      if (response.ok) {
        login(data.token); // Armazena o token
      } else {
        console.error('Erro ao fazer login:', data);
      }
    } catch (error) {
      console.error('Erro ao fazer login:', error);
    }
  };

  return { user, loginUser, logout };
} 