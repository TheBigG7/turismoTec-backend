package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IRolDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImplenent implements  IRolService{

    @Autowired
    private IRolDao rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> findAll() {
        return rolDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rol findById(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Rol save(Rol rol) {
        return rolDao.save(rol);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rolDao.deleteById(id);
    }
}
