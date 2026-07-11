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
@Table(name = "Mesas")
public class Mesas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idMesa;
    private int numeroMesa;
    private int capacidad;
    private boolean disponibilidad;

    @OneToMany(mappedBy = "mesas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReservaMesa> reservaMesas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRestaurante")
    private Restaurante restaurante;

    @OneToMany(mappedBy = "mesas", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImagenesMesas> imagenesMesas;
}
