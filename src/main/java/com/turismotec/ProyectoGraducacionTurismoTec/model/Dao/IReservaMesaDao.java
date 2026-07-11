package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReservaMesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface IReservaMesaDao extends JpaRepository<ReservaMesa,Long> {


    @Query(value = "SELECT YEAR(fechaReserva) AS año, MONTH(fechaReserva) AS mes, " +
            "COUNT(*) AS total_reservas " +
            "FROM ReservaMesa r " +
            "JOIN r.mesas h " +
            "JOIN h.restaurante ho " +
            "WHERE YEAR(fechaReserva) = :year " +
            "AND ho.usuario.id_Usuario = :idPersona " +
            "GROUP BY YEAR(fechaReserva), MONTH(fechaReserva) " +
            "ORDER BY año DESC, mes DESC")
    public List<String> reservasDeMesasPorYearYMes(@Param("year") int year, @Param("idPersona") Long idPersona);

    @Query(value = "SELECT YEAR(fechaReserva) AS año, MONTH(fechaReserva) AS mes, " +
            "COUNT(*) AS total_reservas " +
            "FROM ReservaMesa r " +
            "WHERE YEAR(fechaReserva) = :year " +
            "GROUP BY YEAR(fechaReserva), MONTH(fechaReserva) " +
            "ORDER BY año DESC, mes DESC")
    public List<String> reservasDeMesasPorYearYMesParaAdmin(@Param("year") int year);
}
