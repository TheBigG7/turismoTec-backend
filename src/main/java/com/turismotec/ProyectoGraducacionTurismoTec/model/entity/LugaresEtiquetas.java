package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import java.io.Serializable;

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
@Table(name = "LugaresEtiqueta")
public class LugaresEtiquetas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idLugaresEtiquetas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEtiqueta")
    private EtiquetasLugares etiquetas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idLugares")
    private Lugares lugares;

}
