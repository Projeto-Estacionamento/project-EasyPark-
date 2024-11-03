import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Login } from "./pages/login/login";
import { AdminHome } from "./pages/admin/AdminHome";
import { CaixaHome } from "./pages/caixa/CaixaHome";
import { ProtectedRoute } from "./components/ProtectedRoute";
import { AuthProvider } from "./context/AuthContext";

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
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
          {/* Outras rotas */}
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
