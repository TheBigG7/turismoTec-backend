package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IReservaMesaDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReservaMesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Service
public class ReservaMesaServiceImplement implements IReservaMesaService {

    @Autowired
    public IReservaMesaDao reservaMesaDao;

    @Override
    @Transactional(readOnly = true)
    public List<ReservaMesa> findAll() {
        return reservaMesaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ReservaMesa findById(Long id) {
        return reservaMesaDao.findById(id).orElse(null);
    }

    @Override
    public ReservaMesa save(ReservaMesa reservaMesa) {
        return reservaMesaDao.save(reservaMesa);
    }

    @Override
    public void delete(Long id) {
        reservaMesaDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> reservasDeMesasPorYearYMes(int year, Long idPersona) {
        return reservaMesaDao.reservasDeMesasPorYearYMes(year, idPersona);
    }

    @Override
    public List<String> reservasDeMesasPorYearYMesParaAdmin(int year) {
        return reservaMesaDao.reservasDeMesasPorYearYMesParaAdmin(year);
    }

}
