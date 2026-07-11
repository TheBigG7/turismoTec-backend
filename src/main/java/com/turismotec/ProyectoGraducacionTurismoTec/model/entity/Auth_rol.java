package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Auth_rol")
public class Auth_rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRolId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_auth")
    private Auth auth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rolId")
    private Rol rol;

}
