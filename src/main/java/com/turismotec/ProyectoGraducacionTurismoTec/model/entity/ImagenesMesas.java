package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

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
@Table(name = "ImagenesMesas")
public class ImagenesMesas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagenesMesas;
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMesa")
    private Mesas mesas;

}

