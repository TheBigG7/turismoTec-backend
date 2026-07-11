package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Auth", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Auth implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_auth;
    private String username;
    private String password;
    private Long id_usuario;

    @OneToMany(mappedBy = "auth", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Auth_rol> authRols;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Recorremos los roles del usuario y los convertimos en GrantedAuthority
        authRols.forEach(authRol -> {
            String roleName = authRol.getRol().getRolNombre(); // Obtenemos el nombre del rol
            authorities.add(new SimpleGrantedAuthority(roleName)); // Creamos un GrantedAuthority
        });
        return authorities; // Retornamos la colección de GrantedAuthority
    }



}
