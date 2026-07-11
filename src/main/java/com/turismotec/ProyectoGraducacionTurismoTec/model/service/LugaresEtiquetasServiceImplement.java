package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.ILugaresEtiquetasDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.LugaresEtiquetas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LugaresEtiquetasServiceImplement implements ILugaresEtiquetasService {

    @Autowired
    public ILugaresEtiquetasDao lugaresEtiquetasDao;

    @Override
    @Transactional(readOnly = true)
    public List<LugaresEtiquetas> findAll() {
        return lugaresEtiquetasDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public LugaresEtiquetas findById(Long id) {
        return lugaresEtiquetasDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public LugaresEtiquetas save(LugaresEtiquetas lugaresEtiquetas) {
        return lugaresEtiquetasDao.save(lugaresEtiquetas);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        lugaresEtiquetasDao.deleteById(id);
    }

    public boolean existsByLugarAndEtiqueta(Long idLugar, Long idEtiqueta) {
        return lugaresEtiquetasDao.existsByLugarAndEtiqueta(idLugar, idEtiqueta);
    }
}
