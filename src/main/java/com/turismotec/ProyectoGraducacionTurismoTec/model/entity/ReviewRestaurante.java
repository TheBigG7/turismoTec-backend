package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ReviewRestaurante")
public class ReviewRestaurante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idReviewRestaurante;
    private Date fechaReview;
    private String contenido;
    private double calificacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRestaurante")
    private Restaurante restaurante;
}
