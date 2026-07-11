package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "Hoteles")
public class Hoteles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idHotel;
    private String nombre;
    @Column(length = 500)
    private String direccion;
    private String telefono;
    @Column(length = 500)
    private String descripcion;


    @ManyToMany(mappedBy = "Hoteles") // mappedBy indica el lado inverso de la relación
    private List<EtiquetasHoteles> etiquetasHoteles;

    @OneToMany(mappedBy = "hoteles", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReviewHotel> reviewHoteles;

    @OneToMany(mappedBy = "hoteles",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Habitaciones> habitaciones;

    @OneToMany(mappedBy = "hoteles",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImagenesHoteles> imagenesHoteles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idLugares")
    private Lugares lugares;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;
}
