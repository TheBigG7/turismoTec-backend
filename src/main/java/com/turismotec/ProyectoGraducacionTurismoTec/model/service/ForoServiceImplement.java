package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IForoDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Foro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ForoServiceImplement implements IForoService{

    @Autowired
    public IForoDao foroDao;

    @Override
    @Transactional(readOnly = true)
    public List<Foro> findAll() {
        return foroDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Foro findById(Long id) {
        return foroDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Foro save(Foro foro) {
        return foroDao.save(foro);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        foroDao.deleteById(id);
    }
}
