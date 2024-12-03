import React, { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    useEffect(() => {
        const checkAuth = () => {
            const token = sessionStorage.getItem('token');
            const accessType = sessionStorage.getItem('accessType');
            if (!token || !accessType) {
                sessionStorage.removeItem('token');
                sessionStorage.removeItem('accessType');
                setIsAuthenticated(false);
            } else {
                setIsAuthenticated(true);
            }
        };

        // Verifica o token e o tipo de acesso ao carregar
        checkAuth();

        // Adiciona um listener para mudanças no sessionStorage
        window.addEventListener('storage', checkAuth);

        // Remove o listener ao desmontar
        return () => {
            window.removeEventListener('storage', checkAuth);
        };
    }, []);

    const login = (token, accessType) => {
        sessionStorage.setItem('token', token);
        sessionStorage.setItem('accessType', accessType);
        setIsAuthenticated(true);
    };

    const logout = () => {
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('accessType');
        setIsAuthenticated(false);
    };

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};