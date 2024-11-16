package com.backend.EasyPark.service;

import com.backend.EasyPark.entities.Acesso;
import com.backend.EasyPark.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AcessoRepository acessoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Acesso acesso = acessoRepository.findByUsername(username);
        if (acesso == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return new org.springframework.security.core.userdetails.User(acesso.getUsername(), acesso.getSenha(), new ArrayList<>());
    }
}
