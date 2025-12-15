import React, { useState } from 'react';
import HealthDashboard from './HealthDashboard';
import LoginScreen from './LoginScreen';

function App() {
  // Estado que controla se o usuário está autenticado
  // Começa como 'false' para mostrar a tela de login primeiro
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  // Função chamada quando clicar em "Entrar" na tela de login
  const handleLogin = () => {
    setIsAuthenticated(true);
  };

  // Função chamada quando clicar em "Sair" no perfil
  const handleLogout = () => {
    setIsAuthenticated(false);
  };

  return (
    <>
      {isAuthenticated ? (
        // Se estiver logado, mostra o App Principal
        <HealthDashboard onLogout={handleLogout} />
      ) : (
        // Se NÃO estiver logado, mostra a Tela de Login
        <LoginScreen onLogin={handleLogin} />
      )}
    </>
  );
}

export default App;