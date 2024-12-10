import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export function Card({ title, children }) {
  const navigate = useNavigate();

  const handleButtonClick = (onClick, checkAuth) => {
    const token = sessionStorage.getItem('token');
    if (checkAuth && !token) {
      toast.error('Você precisa estar logado para realizar esta ação.');
      navigate('/');
    } else if (onClick) {
      onClick();
    }
  };

  const renderChildren = () => {
    return React.Children.map(children, (child) => {
      if (React.isValidElement(child) && child.type === 'button') {
        const { onClick, checkAuth = true } = child.props;
        return React.cloneElement(child, {
          onClick: () => handleButtonClick(onClick, checkAuth),
        });
      }
      return child;
    });
  };

  return (
    <div className="card text-center my-3" style={{ backgroundColor: '#00838f', maxHeight: '500px', overflowY: 'auto', boxShadow: '0 6px 12px rgba(0, 0, 0, 0.3)', borderRadius: '8px' }}>
      <div className="card-body">
        {title && <h2 className="card-title">{title}</h2>}
        {renderChildren()}
      </div>
    </div>
  );
} 