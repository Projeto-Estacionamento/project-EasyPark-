import React, { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    useEffect(() => {
        const token = sessionStorage.getItem('token');
        setIsAuthenticated(!!token);
        console.log('Token:', token);
        console.log('isAuthenticated:', !!token);
    }, [isAuthenticated]);

    const login = (token) => {
        sessionStorage.setItem('token', token);
        setIsAuthenticated(true);
    };

    const logout = () => {
        sessionStorage.removeItem('token');
        setIsAuthenticated(false);
    };

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};