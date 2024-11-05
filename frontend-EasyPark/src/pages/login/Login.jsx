import React, { useContext, useState } from "react";
import { Container } from "../../components/container/Container";
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
    email: "",
    senha: "",
  });

  const handleLogin = async (e) => {
    e.preventDefault();
    if (!valores.email || !valores.senha) {
      alert("Por favor, preencha todos os campos.");
      return;
    }

    try {
      const response = await axios.post(`${import.meta.env.VITE_API_URL}/usuario/login`, valores);
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
      alert("E-mail ou senha incorretos!");
    }
  };

  return (
    <Container>
      <h1>Login</h1>
      <form onSubmit={handleLogin}>
        <div className="div-login">
          <Input
            type="email"
            text="E-mail"
            name="email"
            placeholder="E-mail"
            value={valores.email}
            onChangeFN={(e) => setValores({ ...valores, email: e.target.value })}
          />
        </div>
        <div className="div-login">
          <Input
            type="password"
            text="Senha"
            name="senha"
            placeholder="Senha"
            value={valores.senha}
            onChangeFN={(e) => setValores({ ...valores, senha: e.target.value })}
          />
        </div>
        <Button type="submit" color="blue">
          Login
        </Button>
      </form>
    </Container>
  );
}
