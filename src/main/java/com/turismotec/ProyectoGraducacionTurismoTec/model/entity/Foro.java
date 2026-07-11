package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Foro")
public class Foro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idForo;
    private String titulo;
    private String descripcion;

    @OneToMany(mappedBy = "foro" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Publicaciones> publicaciones;

    @OneToMany(mappedBy = "foro", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Usuario> usuarios;
}
