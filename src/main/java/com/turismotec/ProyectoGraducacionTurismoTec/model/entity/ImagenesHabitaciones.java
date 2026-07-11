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
@Table(name = "ImagenesHabitaciones")
public class ImagenesHabitaciones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idImagenHabitacion;
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idHabitacion")
    private Habitaciones habitacion;

}
