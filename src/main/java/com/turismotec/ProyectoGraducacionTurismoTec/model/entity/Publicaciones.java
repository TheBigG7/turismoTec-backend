package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Publicaciones")
public class Publicaciones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idPublicaciones;
    private String titulo;
    private String contenido;
    private String urlFotoForo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idForo")
    private Foro foro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "publicaciones", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comentarios> comentario;
}
