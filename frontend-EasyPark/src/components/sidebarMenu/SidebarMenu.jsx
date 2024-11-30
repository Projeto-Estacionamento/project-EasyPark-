import React, { useContext, useState } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import {
  FiGrid,
  FiFileText,
  FiSettings,
  FiUsers,
  FiLogOut,
  FiChevronDown,
  FiChevronUp,
  FiUser,
  FiPackage,
  FiAperture,
  FiClipboard,
  FiLock,
} from "react-icons/fi";
import logo from "../../assets/logo.png";
import { AuthContext } from "../../context/AuthContext";
import "./SidebarMenu.css";

export function SidebarMenu() {
  const { logout } = useContext(AuthContext);
  const navigate = useNavigate();
  const location = useLocation();
  const [isGerenciamentoOpen, setIsGerenciamentoOpen] = useState(false);
  const [isConfiguracaoOpen, setIsConfiguracaoOpen] = useState(false);

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const gerenciamentoSubItems = [
    {
      path: "/gerenciamento-cliente",
      icon: <FiUser size={16} />,
      label: "Cliente",
    },
    {
      path: "/gerenciamento-veiculo",
      icon: <FiAperture size={16} />,
      label: "Veículo",
    },
    {
      path: "/gerenciamento-plano",
      icon: <FiPackage size={16} />,
      label: "Plano",
    },
    {
      path: "/gerenciamento-assinatura-plano",
      icon: <FiClipboard size={16} />,
      label: "Assinatura",
    },
  ];

  const configuracaoSubItems = [
    {
      path: "/configuracao-acesso",
      icon: <FiLock size={16} />,
      label: "Acesso",
    },
    {
      path: "/configuracao-estacionamento",
      icon: <FiSettings size={16} />,
      label: "Estacionamento",
    },
  ];

  const menuItems = [
    { path: "/home", icon: <FiGrid size={20} />, label: "Home" },
    { path: "/relatorio", icon: <FiFileText size={20} />, label: "Relatório" },
  ];

  return (
    <div className="sidebar">
      <div className="sidebar-logo">
        <img src={logo} alt="Logo" />
      </div>

      <nav className="nav-menu">
        {menuItems.map((item) => (
          <Link
            key={item.path}
            to={item.path}
            className={`nav-item ${
              location.pathname === item.path ? "active" : ""
            }`}
          >
            <span className="nav-icon">{item.icon}</span>
            <span className="nav-text">{item.label}</span>
          </Link>
        ))}

        <div className="nav-item-with-submenu">
          <button
            className={`nav-item ${
              location.pathname.includes("/configuracao") ? "active" : ""
            }`}
            onClick={() => setIsConfiguracaoOpen(!isConfiguracaoOpen)}
          >
            <span className="nav-icon">
              <FiSettings size={20} />
            </span>
            <span className="nav-text">Configuração</span>
            <span className="submenu-icon">
              {isConfiguracaoOpen ? (
                <FiChevronUp size={16} />
              ) : (
                <FiChevronDown size={16} />
              )}
            </span>
          </button>
          {isConfiguracaoOpen && (
            <div className="submenu">
              {configuracaoSubItems.map((subItem) => (
                <Link
                  key={subItem.path}
                  to={subItem.path}
                  className={`submenu-item ${
                    location.pathname === subItem.path ? "active" : ""
                  }`}
                >
                  <span className="nav-icon">{subItem.icon}</span>
                  <span className="nav-text">{subItem.label}</span>
                </Link>
              ))}
            </div>
          )}
        </div>

        <div className="nav-item-with-submenu">
          <button
            className={`nav-item ${
              location.pathname.includes("/gerenciamento") ? "active" : ""
            }`}
            onClick={() => setIsGerenciamentoOpen(!isGerenciamentoOpen)}
          >
            <span className="nav-icon">
              <FiUsers size={20} />
            </span>
            <span className="nav-text">Gerenciamento</span>
            <span className="submenu-icon">
              {isGerenciamentoOpen ? (
                <FiChevronUp size={16} />
              ) : (
                <FiChevronDown size={16} />
              )}
            </span>
          </button>

          {isGerenciamentoOpen && (
            <div className="submenu">
              {gerenciamentoSubItems.map((subItem) => (
                <Link
                  key={subItem.path}
                  to={subItem.path}
                  className={`submenu-item ${
                    location.pathname === subItem.path ? "active" : ""
                  }`}
                >
                  <span className="nav-icon">{subItem.icon}</span>
                  <span className="nav-text">{subItem.label}</span>
                </Link>
              ))}
            </div>
          )}
        </div>
      </nav>

      <button onClick={handleLogout} className="logout-button">
        <span className="nav-icon">
          <FiLogOut size={20} />
        </span>
        <span className="nav-text">Logout</span>
      </button>
    </div>
  );
}
