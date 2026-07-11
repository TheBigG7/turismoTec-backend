package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReservasHabitaciones;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewHotel;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IReservasHabitacionesService {

    public List<ReservasHabitaciones> findAll();

    public ReservasHabitaciones findById(Long id);

    public ReservasHabitaciones save(ReservasHabitaciones reservasHabitaciones);

    public void delete(Long id);

    public List<Date> fechasNoDisponibles(Long id);

    public List<String> reservasPorYearYMes(int year, Long idPersona);

    public List<String> reservasPorYearYMesParaAdmin(int year);
}
