package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

    @Query("SELECT YEAR(fechaRegistro) AS año, MONTH(fechaRegistro) AS mes, COUNT(*) AS total_usuarios\n" +
            "FROM Usuario WHERE YEAR(fechaRegistro) = :year\n" +
            "GROUP BY YEAR(fechaRegistro), MONTH(fechaRegistro)\n" +
            "ORDER BY año DESC, mes DESC")
    public List<String> CantidadPersonasRegistradasByMesYYear(@Param("year") int year);

    @Query("SELECT COUNT(*) FROM Usuario")
    public Long totalUsuarioRegistrados();


    @Query("SELECT paisOrigen, COUNT(*) AS total_usuarios\n" +
            "FROM Usuario\n" +
            "GROUP BY paisOrigen\n" +
            "ORDER BY total_usuarios DESC")
    public List<String> usuarioPorPaisOrigen();

    public List<Usuario> findByidpersonaqueloCreo(long idUsuario);
}
