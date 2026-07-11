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
@Table(name = "EtiquetasRestaurantes")
public class EtiquetasRestaurantes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idEtiquetaRestaurante;
    private String etiqueta;

    @ManyToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinTable(
            name = "EtiquetasRestaurantes_Restaurantes", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "idEtiquetaRestaurante"), // FK
            inverseJoinColumns = @JoinColumn(name = "idRestaurante") // FK
    )
    @JsonIgnore
    private List<Restaurante> restaurantes;
}
