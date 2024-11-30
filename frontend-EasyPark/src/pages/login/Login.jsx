import React, { useContext, useState } from "react";
import { PageContainer } from "../../components/pageContainer/PageContainer";
import { Input } from "../../components/input/Input";
import { Button } from "../../components/button/button";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate } from "react-router-dom";
import logo from '../../assets/logo.png';
import './Login.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import mockData from "../../mock/mockData";

export function Login() {
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();
  const [valores, setValores] = useState({
    email: "",
    senha: "",
  });

  const handleLogin = async (e) => {
    e.preventDefault();
    const credentials = btoa(`${valores.email}:${valores.senha}`);
    try {
      // const response = await fetch('http://localhost:8080/easypark/auth/login', {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json',
      //     'Authorization': `Basic ${credentials}`
      //   },
      //   body: JSON.stringify({ login: valores.email, senha: valores.senha }),
      // });

      const data = mockData.acessos.find(acesso => acesso.username === valores.email && acesso.password === valores.senha);

      if (data) {
        // const data = await response.json();
        login('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c');
        sessionStorage.setItem('accessType', data.accessType);
        navigate("/home");
      } else {
        console.error('Erro ao fazer login:', response.statusText);
        toast.error("Email ou senha incorretos!");
      }
    } catch (error) {
      console.error('Erro ao fazer login:', error);
      toast.error("Email ou senha incorretos!");
    }
  };

  return (
    <PageContainer darkMode>
      <ToastContainer position="top-right" autoClose={3000} />
      <div className="login-card text-center my-3" style={{ backgroundColor: '#00838f', boxShadow: '0 6px 12px rgba(0, 0, 0, 0.3)', border: 'none', borderRadius: '8px' }}>
        <img src={logo} alt="Logo" className="img-fluid mb-3 mx-auto" style={{ maxWidth: '150px' }} />
        <form onSubmit={handleLogin}>
          <div className="form-group">
            <Input
              type="text"
              text="Email"
              name="email"
              placeholder="Digite seu email"
              value={valores.email}
              onChangeFN={(e) => setValores({ ...valores, email: e.target.value })}
            />
          </div>
          <div className="form-group">
            <Input
              type="password"
              text="Senha"
              name="senha"
              placeholder="Digite sua senha"
              value={valores.senha}
              onChangeFN={(e) => setValores({ ...valores, senha: e.target.value })}
            />
          </div>
          <Button type="submit" variant="outline-light" fullWidth checkAuth={false}>
            Entrar
          </Button>
        </form>
      </div>
    </PageContainer>
  );
}