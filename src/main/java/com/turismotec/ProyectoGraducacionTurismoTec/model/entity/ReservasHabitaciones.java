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
@Table(name = "ReservasHabitaciones")
public class ReservasHabitaciones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idReservas;
    private Date fechaIncioReserva;
    private Date fechaFinReserva;
    private String horaReserva;
    private boolean estadoReserva;

    // Nuevo campo para controlar si ya se envió la notificación
    @Column(name = "notificacion_enviada")
    private boolean notificacionEnviada = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idHabitacion")
    private Habitaciones habitaciones;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;
}
