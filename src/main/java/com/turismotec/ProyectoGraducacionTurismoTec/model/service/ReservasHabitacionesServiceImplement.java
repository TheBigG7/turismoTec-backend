package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IReservasHabitacionesDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReservasHabitaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ReservasHabitacionesServiceImplement implements IReservasHabitacionesService{

    @Autowired
    public IReservasHabitacionesDao reservasHabitacionesDao;

    @Override
    @Transactional(readOnly = true)
    public List<ReservasHabitaciones> findAll() {
        return reservasHabitacionesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ReservasHabitaciones findById(Long id) {
        return reservasHabitacionesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ReservasHabitaciones save(ReservasHabitaciones reservasHabitaciones) {
        return reservasHabitacionesDao.save(reservasHabitaciones);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        reservasHabitacionesDao.deleteById(id);
    }

    @Override
    public List<Date> fechasNoDisponibles(Long idHabitacion) {
        return reservasHabitacionesDao.fechasReservadas(idHabitacion);
    }

    @Override
    public List<String> reservasPorYearYMes(int year, Long idPersona) {
        return reservasHabitacionesDao.reservasPorYearYMes(year,idPersona);
    }

    @Override
    public List<String> reservasPorYearYMesParaAdmin(int year) {
        return reservasHabitacionesDao.reservasPorYearYMesParaAdmin(year);
    }
}
