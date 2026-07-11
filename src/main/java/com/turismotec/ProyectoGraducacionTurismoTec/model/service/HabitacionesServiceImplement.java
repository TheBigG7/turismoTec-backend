package com.turismotec.ProyectoGraducacionTurismoTec.model.service;


import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IHabitacionesDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Habitaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HabitacionesServiceImplement implements IHabitacionesService{

    @Autowired
    public IHabitacionesDao habitacionesDao;

    @Override
    @Transactional(readOnly = true)
    public List<Habitaciones> findAll() {
        return habitacionesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Habitaciones findById(Long id) {
        return habitacionesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Habitaciones save(Habitaciones habitaciones) {
        return habitacionesDao.save(habitaciones);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        habitacionesDao.deleteById(id);
    }
}
