package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IPublicacionesDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Publicaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublicacionesServiceImplement implements IPublicacionesService{

    @Autowired
    public IPublicacionesDao publicacionesDao;

    @Override
    @Transactional(readOnly = true)
    public List<Publicaciones> findAll() {
        return publicacionesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Publicaciones findById(Long id) {
        return publicacionesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Publicaciones save(Publicaciones publicaciones) {
        return publicacionesDao.save(publicaciones);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        publicacionesDao.deleteById(id);
    }
}
