package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Auth_rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAuth_RolDao extends JpaRepository<Auth_rol,Long> {

    @Query("SELECT COUNT(ur) > 0 FROM Auth_rol ur WHERE ur.auth.id_auth = :idAuth AND ur.rol.rolId = :idRol")
    boolean existsByAuthAndRol(@Param("idAuth") Long idAuth, @Param("idRol") Long idRol);
}
