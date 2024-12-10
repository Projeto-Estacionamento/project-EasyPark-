package com.backend.EasyPark.util.validacao;


import com.backend.EasyPark.auth.AuthenticationService;
import com.backend.EasyPark.exception.EstacionamentoException;
import com.backend.EasyPark.model.entities.Acesso;
import com.backend.EasyPark.model.enums.TipoAcesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarTipoAcesso {

    @Autowired
    private AuthenticationService authenticationService;

    public void validarAcessoAdmin() throws EstacionamentoException {
        Acesso acessoAutenticado = authenticationService.getUsuarioAutenticado();
        if (acessoAutenticado == null) {
            throw new EstacionamentoException("Usuário não encontrado.");
        }
        if (acessoAutenticado.getTipoAcesso() == TipoAcesso.CAIXA) {
            throw new EstacionamentoException("Usuário sem permissão.");
        }
    }

    public void validarSeExisteUsuario() throws EstacionamentoException {
        Acesso acessoAutenticado = authenticationService.getUsuarioAutenticado();
        if (acessoAutenticado == null) {
            throw new EstacionamentoException("Usuário não encontrado.");
        }
    }


}
