import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Login } from "./pages/login/Login";
import { Home } from "./pages/Home/Home";
import Gerenciamento from './pages/Gerenciamento/Gerenciamento';
import { AuthProvider } from "./context/AuthContext";
import { GerenciamentoCliente } from "./pages/GerenciamentoCliente/GerenciamentoCliente";
import { GerenciamentoPlano } from "./pages/GerenciamentoPlano/GerenciamentoPlano";
import { GerenciamentoAssinaturaPlano } from "./pages/GerenciamentoAssinaturaPlano/GerenciamentoAssinaturaPlano";
import { Relatorio } from "./pages/Relatorio/Relatorio";
import { ConfiguracaoAcesso } from "./pages/ConfiguracaoAcesso/ConfiguracaoAcesso";
import { ConfiguracaoEstacionamentoAdmin } from "./pages/ConfiguracaoEstacionamento/ConfiguracaoEstacionamentoAdmin";
import { GerenciamentoVeiculo } from './pages/GerenciamentoVeiculo/GerenciamentoVeiculo';
import { Cliente } from './pages/novo/Cliente';
import { Veiculo } from './pages/novo/Veiculo';
import { Plano } from './pages/novo/Plano';
import { Assinatura } from './pages/novo/Assinatura';
import ErrorBoundary from './components/ErrorBoundary';
import ProtectedRoute from "./components/ProtectedRoute";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './App.css';
import { Acesso } from './pages/novo/Acesso';

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className="App">
          <ToastContainer
            position="top-right"
            autoClose={3000}
            hideProgressBar={false}
            newestOnTop={false}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
            theme="light"
          />
          <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/home" element={<ProtectedRoute><Home /></ProtectedRoute>} />
            <Route path="/gerenciamento" element={<ProtectedRoute><Gerenciamento /></ProtectedRoute>} />
            <Route path="/gerenciamento-cliente" element={<ProtectedRoute><GerenciamentoCliente isAdmin={false} /></ProtectedRoute>} />
            <Route path="/gerenciamento-plano" element={<ProtectedRoute><GerenciamentoPlano isAdmin={false} /></ProtectedRoute>} />
            <Route path="/gerenciamento-assinatura-plano" element={<ProtectedRoute><GerenciamentoAssinaturaPlano isAdmin={false} /></ProtectedRoute>} />
            <Route path="/relatorio" element={<ProtectedRoute><Relatorio /></ProtectedRoute>} />
            <Route path="/configuracao-estacionamento" element={<ProtectedRoute><ErrorBoundary><ConfiguracaoEstacionamentoAdmin /></ErrorBoundary></ProtectedRoute>} />
            <Route path="/configuracao-acesso" element={<ProtectedRoute adminOnly><ConfiguracaoAcesso /></ProtectedRoute>} />
            <Route path="/gerenciamento-veiculo" element={<ProtectedRoute><GerenciamentoVeiculo /></ProtectedRoute>} />
            <Route path="/novo/cliente" element={<ProtectedRoute><Cliente /></ProtectedRoute>} />
            <Route path="/novo/veiculo" element={<ProtectedRoute><Veiculo /></ProtectedRoute>} />
            <Route path="/novo/plano" element={<ProtectedRoute><Plano /></ProtectedRoute>} /> 
            <Route path="/novo/assinatura" element={<ProtectedRoute><Assinatura /></ProtectedRoute>} />
            <Route path="/novo/acesso" element={<ProtectedRoute><Acesso /></ProtectedRoute>} />
          </Routes>
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;
