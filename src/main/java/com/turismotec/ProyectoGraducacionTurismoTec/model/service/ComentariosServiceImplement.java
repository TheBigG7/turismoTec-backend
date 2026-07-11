package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IComentariosDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Comentarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComentariosServiceImplement implements IComentariosService {

    @Autowired
    public IComentariosDao comentariosDao;

    @Override
    @Transactional(readOnly = true)
    public List<Comentarios> findAll() {
        return comentariosDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Comentarios findById(Long id) {
        return comentariosDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Comentarios save(Comentarios comentarios) {
        return comentariosDao.save(comentarios);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        comentariosDao.deleteById(id);
    }
}
