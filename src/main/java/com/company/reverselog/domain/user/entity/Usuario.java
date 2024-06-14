package com.company.reverselog.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "tb_usuarios")
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String login;

    private String senha;

    private boolean isAdmin = false;

    public Usuario(String email, String password) {
        this.login = email;
        this.senha = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (isAdmin) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            );
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
