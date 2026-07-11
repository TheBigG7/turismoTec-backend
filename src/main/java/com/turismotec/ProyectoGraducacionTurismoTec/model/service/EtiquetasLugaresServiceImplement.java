package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IEtiquetasLugaresDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasLugares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EtiquetasLugaresServiceImplement implements IEtiquetasLugaresService {

    @Autowired
    public IEtiquetasLugaresDao etiquetasDao;

    @Override
    @Transactional(readOnly = true)
    public List<EtiquetasLugares> findAll() {
        return etiquetasDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EtiquetasLugares findById(Long id) {
        return etiquetasDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public EtiquetasLugares save(EtiquetasLugares categorias) {
        return etiquetasDao.save(categorias);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        etiquetasDao.deleteById(id);
    }
}
