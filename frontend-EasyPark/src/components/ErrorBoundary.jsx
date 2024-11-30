import React from 'react';

class ErrorBoundary extends React.Component {
  constructor(props) {
    super(props);
    this.state = { hasError: false };
  }

  static getDerivedStateFromError(error) {
    // Atualiza o estado para que a próxima renderização mostre a UI de fallback
    return { hasError: true };
  }

  componentDidCatch(error, errorInfo) {
    // Você pode registrar o erro em um serviço de relatórios de erro
    console.error("ErrorBoundary capturou um erro:", error, errorInfo);
  }

  render() {
    if (this.state.hasError) {
      // Você pode renderizar qualquer UI de fallback
      return <h1>Algo deu errado. Por favor, tente novamente mais tarde.</h1>;
    }

    return this.props.children; 
  }
}

export default ErrorBoundary; 