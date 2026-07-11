package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IInicioDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Inicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class InicioServiceImp implements IInicioService {

    @Autowired
    public IInicioDao inicioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Inicio> findAll() {
        return inicioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Inicio findById(Long id) {
        return inicioDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Inicio save(Inicio inicio) {
        return inicioDao.save(inicio);
    }

    @Override
    public void delete(Long id) {
        inicioDao.deleteById(id);
    }
}
