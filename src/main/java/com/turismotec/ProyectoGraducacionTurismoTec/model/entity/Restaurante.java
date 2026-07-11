package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Restaurante")
public class Restaurante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idRestaurante;
    private String nombre;
    private String direccion;
    private String telefono;
    private String descripcion;
    private String menu;
    private String horaDeApertura;
    private String horaDeCierre;

    //TODO: Meter en el formulario
    private boolean visualizacion = true;
    private boolean aprobado;


    @ManyToMany(mappedBy = "restaurantes") // mappedBy indica el lado inverso de la relación
    private List<EtiquetasRestaurantes> etiquetasRestaurantes;

    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Mesas> mesas;

    @OneToMany(mappedBy = "restaurante", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReviewRestaurante> reviewRestaurantes;

    @OneToMany(mappedBy = "restaurante", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ImagenesRestaurantes> imagenesRestaurantes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idLugares")
    private Lugares lugares;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

}
