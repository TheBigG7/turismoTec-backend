package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IEtiquetasRestaurantesDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasRestaurantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EtiquetasRestaurantesServiceImplement implements IEtiquetasRestaurantesService {

    @Autowired
    public IEtiquetasRestaurantesDao etiquetasDao;

    @Override
    @Transactional(readOnly = true)
    public List<EtiquetasRestaurantes> findAll() {
        return etiquetasDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EtiquetasRestaurantes findById(Long id) {
        return etiquetasDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public EtiquetasRestaurantes save(EtiquetasRestaurantes categorias) {
        return etiquetasDao.save(categorias);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        etiquetasDao.deleteById(id);
    }

}
