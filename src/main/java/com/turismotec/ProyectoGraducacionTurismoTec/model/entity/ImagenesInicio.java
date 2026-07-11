package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ImagenesInicio")
public class ImagenesInicio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idImagenInicio;
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idInicio")
    private Inicio inicio;

}
