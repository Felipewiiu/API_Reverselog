package com.company.reverselog.domain.user.service;

import com.company.reverselog.domain.user.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class  AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository userRepository;

    public AutenticacaoService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var loudUser = userRepository.findByLogin(username);

        return loudUser;

    }

}
