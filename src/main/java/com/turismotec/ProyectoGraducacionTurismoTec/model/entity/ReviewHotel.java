package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table(name = "ReviewHotel")
public class ReviewHotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idReviewHotel;
    private Date fechaReview = new Date();
    private String contenido;
    private double calificacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idHotel")
    private Hoteles hoteles;

}




