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
@Table(name = "EtiquetasLugares")
public class EtiquetasLugares implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idEtiqueta;
    private String etiqueta;

    @OneToMany(mappedBy = "etiquetas", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<LugaresEtiquetas> lugaresEtiquetas;
}
