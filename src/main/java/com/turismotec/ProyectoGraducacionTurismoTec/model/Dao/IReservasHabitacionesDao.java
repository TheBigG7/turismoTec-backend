package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReservasHabitaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IReservasHabitacionesDao extends JpaRepository<ReservasHabitaciones,Long> {

    @Query(value = "WITH RECURSIVE FechasIntermedias AS (" +
            "    SELECT fecha_incio_reserva AS fecha, fecha_fin_reserva " +
            "    FROM reservas_habitaciones " +
            "    WHERE id_habitacion = :idHabitacion " +  // Filtra por id_habitacion en el paso inicial
            "    UNION ALL " +
            "    SELECT DATE_ADD(fecha, INTERVAL 1 DAY), fecha_fin_reserva " +
            "    FROM FechasIntermedias " +
            "    WHERE fecha < fecha_fin_reserva" +
            ") " +
            "SELECT DISTINCT fecha " +
            "FROM FechasIntermedias " +
            "ORDER BY fecha",
            nativeQuery = true)
    List<Date> fechasReservadas(@Param("idHabitacion") Long idHabitacion);


    @Query(value = "SELECT YEAR(fechaIncioReserva) AS año, MONTH(fechaIncioReserva) AS mes, " +
            "COUNT(*) AS total_reservas " +
            "FROM ReservasHabitaciones r " +
            "JOIN r.habitaciones h " +
            "JOIN h.hoteles ho " +
            "WHERE YEAR(fechaIncioReserva) = :year " +
            "AND ho.usuario.id_Usuario = :idPersona " +
            "GROUP BY YEAR(fechaIncioReserva), MONTH(fechaIncioReserva) " +
            "ORDER BY año DESC, mes DESC")
    public List<String> reservasPorYearYMes(@Param("year") int year, @Param("idPersona") Long idPersona);

    @Query(value = "SELECT YEAR(fechaIncioReserva) AS año, MONTH(fechaIncioReserva) AS mes, " +
            "COUNT(*) AS total_reservas " +
            "FROM ReservasHabitaciones r " +
            "WHERE YEAR(fechaIncioReserva) = :year " +
            "GROUP BY YEAR(fechaIncioReserva), MONTH(fechaIncioReserva) " +
            "ORDER BY año DESC, mes DESC")
    public List<String> reservasPorYearYMesParaAdmin(@Param("year") int year);

}
