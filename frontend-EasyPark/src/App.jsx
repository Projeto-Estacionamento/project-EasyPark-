import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Login } from "./pages/login/Login";
import { AdminHome } from "./pages/AdminHome/AdminHome";
import { CaixaHome } from "./pages/CaixaHome/CaixaHome";
import { ProtectedRoute } from "./components/ProtectedRoute";
import { AuthProvider } from "./context/AuthContext";
import { ConfiguracaoEstacionamento } from "./pages/ConfiguracaoEstacionamento/ConfiguracaoEstacionamento";
import { GerenciamentoCliente } from "./pages/GerenciamentoCliente/GerenciamentoCliente";
import { GerenciamentoPlano } from "./pages/GerenciamentoPlano/GerenciamentoPlano";
import { GerenciamentoAssinaturaPlano } from "./pages/GerenciamentoAssinaturaPlano/GerenciamentoAssinaturaPlano";
import { Relatorio } from "./pages/Relatorio/Relatorio";
import { ConfiguracaoAcesso } from "./pages/ConfiguracaoAcesso/ConfiguracaoAcesso";

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          {/* <Route path="/" element={<Login />} />
          <Route
            path="/admin"
            element={
              <ProtectedRoute allowedRoles={['ADMINISTRADOR']}>
                <AdminHome />
              </ProtectedRoute>
            }
          />
          <Route
            path="/caixa"
            element={
              <ProtectedRoute allowedRoles={['CAIXA']}>
                <CaixaHome />
              </ProtectedRoute>
            }
          />
          <Route
            path="/configuracao-estacionamento"
            element={
              <ProtectedRoute allowedRoles={['ADMINISTRADOR', 'CAIXA']}>
                <ConfiguracaoEstacionamento isAdmin={false} />
              </ProtectedRoute>
            }
          />
          <Route
            path="/gerenciamento-cliente"
            element={
              <ProtectedRoute allowedRoles={['ADMINISTRADOR', 'CAIXA']}>
                <GerenciamentoCliente isAdmin={false} />
              </ProtectedRoute>
            }
          />
          <Route
            path="/gerenciamento-plano"
            element={
              <ProtectedRoute allowedRoles={['ADMINISTRADOR', 'CAIXA']}>
                <GerenciamentoPlano isAdmin={false} />
              </ProtectedRoute>
            }
          />
          <Route
            path="/gerenciamento-assinatura"
            element={
              <ProtectedRoute allowedRoles={['ADMINISTRADOR', 'CAIXA']}>
                <GerenciamentoAssinaturaPlano isAdmin={false} />
              </ProtectedRoute>
            }
          />
          <Route
            path="/relatorio"
            element={
              <ProtectedRoute allowedRoles={['ADMINISTRADOR', 'CAIXA']}>
                <Relatorio />
              </ProtectedRoute>
            }
          />
          <Route
            path="/configuracao-acesso"
            element={
              <ProtectedRoute allowedRoles={['ADMINISTRADOR']}>
                <ConfiguracaoAcesso />
              </ProtectedRoute>
            }
          /> */}
        <Route path="/" element={<Login />} />
          <Route path="/admin" element={<AdminHome />} />
          <Route path="/caixa" element={<CaixaHome />} />
          <Route path="/configuracao-estacionamento" element={<ConfiguracaoEstacionamento isAdmin={false} />} />
          <Route path="/gerenciamento-cliente" element={<GerenciamentoCliente isAdmin={false} />} />
          <Route path="/gerenciamento-plano" element={<GerenciamentoPlano isAdmin={false} />} />
          <Route path="/gerenciamento-assinatura" element={<GerenciamentoAssinaturaPlano isAdmin={false} />} />
          <Route path="/relatorio" element={<Relatorio />} />
          <Route path="/configuracao-acesso" element={<ConfiguracaoAcesso />} />  
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
