import React, { useContext, useState } from "react";
import { PageContainer } from "../../components/pageContainer/PageContainer";
import { Input } from "../../components/input/Input";
import { Button } from "../../components/button/button";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate } from "react-router-dom";
import logo from '../../assets/logo.png';
import './Login.css';

export function Login() {
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();
  const [valores, setValores] = useState({
    username: "",
    senha: "",
  });

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/acesso/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username: valores.username, senha: valores.senha }),
      });

      if (response.ok) {
        const data = await response.json();
        login(data);
        if (data.tipoAcesso === "ADMINISTRADOR") {
          navigate("/admin");
        } else if (data.tipoAcesso === "CAIXA") {
          navigate("/caixa");
        }
      } else {
        console.error('Erro ao fazer login:', response.statusText);
        alert("Username ou senha incorretos!");
      }
    } catch (error) {
      console.error('Erro ao fazer login:', error);
      alert("Username ou senha incorretos!");
    }
  };

  return (
    <PageContainer darkMode>
      <div className="login-card text-center my-3" style={{ backgroundColor: '#00838f', boxShadow: '0 6px 12px rgba(0, 0, 0, 0.3)', border: 'none', borderRadius: '8px' }}>
        <img src={logo} alt="Logo" className="img-fluid mb-3 mx-auto" style={{ maxWidth: '150px' }} />
        <form onSubmit={handleLogin}>
          <div className="form-group">
            <Input
              type="text"
              text="Username"
              name="username"
              placeholder="Digite seu username"
              value={valores.username}
              onChangeFN={(e) => setValores({ ...valores, username: e.target.value })}
              darkMode
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
              darkMode
            />
          </div>
          <Button type="submit" variant="outline-light" fullWidth>
            Entrar
          </Button>
        </form>
      </div>
    </PageContainer>
  );
}