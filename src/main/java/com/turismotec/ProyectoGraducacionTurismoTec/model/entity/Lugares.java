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
@Table(name = "Lugares")
public class Lugares implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idLugares;
    private String nombre;
    @Column(length = 1000)
    private String descripcion;
    @Column(length = 500)
    private String direccion;
    private float latitud;
    private float longitud;
    private boolean areaProtegida;
    private boolean patrimonio;
    private String tipoZona;
    //TODO: Meter en el formulario
    private boolean visualizacion = true;
    private boolean aprobado;
    private boolean creadoPor;

    @OneToMany(mappedBy = "lugares", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LugaresEtiquetas> lugaresEtiquetas;

    @OneToMany(mappedBy = "lugares", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReviewPlaces> reviewPlaces;

    @OneToMany(mappedBy = "lugares", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Hoteles> hoteles;

    @OneToMany(mappedBy = "lugares", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Restaurante> restaurantes;

    @OneToMany(mappedBy = "lugares",fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<ImagenesLugar> imagenesLugars;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

}
