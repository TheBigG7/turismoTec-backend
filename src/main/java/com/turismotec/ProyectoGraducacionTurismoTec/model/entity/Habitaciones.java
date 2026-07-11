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
@Table(name = "Habitaciones")
public class Habitaciones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idHabitacion;
    private String nombreHabitacion;//
    private String descripcion;//
    private boolean disponible;
    private double precio;//
    private int capacidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idHotel")
    private Hoteles hoteles;

    @OneToMany(mappedBy = "habitaciones", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReservasHabitaciones> reservasHabitaciones;

    @OneToMany(mappedBy = "habitacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImagenesHabitaciones> imagenesHabitaciones;
}
