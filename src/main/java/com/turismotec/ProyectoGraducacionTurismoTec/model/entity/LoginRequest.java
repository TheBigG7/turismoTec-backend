package com.turismotec.ProyectoGraducacionTurismoTec.model.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
    private Long id_usuario;
}
