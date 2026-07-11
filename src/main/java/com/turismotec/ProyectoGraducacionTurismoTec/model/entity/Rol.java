package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rol")
public class Rol {
    @Id
    private Long rolId;
    private String rolNombre;

    @OneToMany(mappedBy = "rol", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Auth_rol> usuarioRols;
}
