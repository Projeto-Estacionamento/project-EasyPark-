import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Login } from "./pages/login/Login";
import { Home } from "./pages/Home/Home";
import ProtectedRoute from "./components/ProtectedRoute";
import { AuthProvider } from "./context/AuthContext";
import { GerenciamentoCliente } from "./pages/GerenciamentoCliente/GerenciamentoCliente";
import { GerenciamentoPlano } from "./pages/GerenciamentoPlano/GerenciamentoPlano";
import { GerenciamentoAssinaturaPlano } from "./pages/GerenciamentoAssinaturaPlano/GerenciamentoAssinaturaPlano";
import { Relatorio } from "./pages/Relatorio/Relatorio";
import { ConfiguracaoAcesso } from "./pages/ConfiguracaoAcesso/ConfiguracaoAcesso";
import { ConfiguracaoEstacionamentoAdmin } from "./pages/ConfiguracaoEstacionamento/ConfiguracaoEstacionamentoAdmin";
import ErrorBoundary from './components/ErrorBoundary';
import './App.css';
import Gerenciamento from './pages/Gerenciamento/Gerenciamento';
import Configuracao from './pages/Configuracao/Configuracao';

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className="App">
          <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/home" element={<ProtectedRoute><Home /></ProtectedRoute>} />
            <Route path="/gerenciamento" element={<ProtectedRoute><Gerenciamento /></ProtectedRoute>} />
            <Route path="/gerenciamento-cliente" element={<ProtectedRoute><GerenciamentoCliente isAdmin={false} /></ProtectedRoute>} />
            <Route path="/gerenciamento-plano" element={<ProtectedRoute><GerenciamentoPlano isAdmin={false} /></ProtectedRoute>} />
            <Route path="/gerenciamento-assinatura-plano" element={<ProtectedRoute><GerenciamentoAssinaturaPlano isAdmin={false} /></ProtectedRoute>} />
            <Route path="/relatorio" element={<ProtectedRoute><Relatorio /></ProtectedRoute>} />
            <Route path="/configuracao" element={<ProtectedRoute adminOnly><Configuracao /></ProtectedRoute>} />
            <Route path="/configuracao-acesso" element={<ProtectedRoute adminOnly><ConfiguracaoAcesso /></ProtectedRoute>} />
            <Route path="/configuracao-estacionamento" element={<ProtectedRoute><ErrorBoundary><ConfiguracaoEstacionamentoAdmin /></ErrorBoundary></ProtectedRoute>} />
          </Routes>
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;
