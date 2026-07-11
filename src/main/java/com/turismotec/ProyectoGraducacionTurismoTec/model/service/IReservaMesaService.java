package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReservaMesa;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface IReservaMesaService {

    public List<ReservaMesa> findAll();
    public ReservaMesa findById(Long id);
    public ReservaMesa save(ReservaMesa reviewHotel);
    public void delete(Long id);
    public List<String> reservasDeMesasPorYearYMes(int year, Long idPersona);
    public List<String> reservasDeMesasPorYearYMesParaAdmin(int year);
}
