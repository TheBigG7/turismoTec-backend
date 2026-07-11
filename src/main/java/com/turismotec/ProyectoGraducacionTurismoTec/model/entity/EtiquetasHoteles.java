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
@Table(name = "EtiquetasHoteles")
public class EtiquetasHoteles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idEtiquetaHoteles;
    private String etiqueta;

    @ManyToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinTable(
            name = "EtiquetasHoteles_Hoteles", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "idEtiquetaHoteles"), // FK de etiquetas
            inverseJoinColumns = @JoinColumn(name = "idHotel") // FK de hoteles
    )
    @JsonIgnore
    private List<Hoteles> Hoteles;
}
