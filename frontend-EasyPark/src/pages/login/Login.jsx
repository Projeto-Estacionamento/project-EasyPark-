import React, { useContext, useState } from "react";
import { PageContainer } from "../../components/pageContainer/PageContainer";
import { Card } from "../../components/card/Card";
import { Input } from "../../components/input/Input";
import { Button } from "../../components/button/Button";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Login.css";

export function Login() {
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();
  const [valores, setValores] = useState({
    username: "",
    senha: "",
  });

  const handleLogin = async (e) => {
    e.preventDefault();
    if (!valores.username || !valores.senha) {
      alert("Por favor, preencha todos os campos.");
      return;
    }

    try {
      const response = await axios.post(
        `${import.meta.env.VITE_API_URL}/api/acesso/login`,
        valores
      );
      if (response.status === 200) {
        login(response.data);
        if (response.data.tipoAcesso === "ADMINISTRADOR") {
          navigate("/admin");
        } else if (response.data.tipoAcesso === "CAIXA") {
          navigate("/caixa");
        }
      }
    } catch (error) {
      console.error(error);
      alert("Username ou senha incorretos!");
    }
  };

  return (
    <PageContainer darkMode>
      <Card title="Login">
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
          <Button type="submit" variant="primary">
            Entrar
          </Button>
        </form>
      </Card>
    </PageContainer>
  );
}
