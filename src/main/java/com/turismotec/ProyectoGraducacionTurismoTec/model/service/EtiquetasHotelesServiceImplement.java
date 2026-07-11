package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IEtiquetasHotelesDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.EtiquetasHoteles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EtiquetasHotelesServiceImplement implements IEtiquetasHotelesService {

    @Autowired
    public IEtiquetasHotelesDao etiquetasDao;

    @Override
    @Transactional(readOnly = true)
    public List<EtiquetasHoteles> findAll() {
        return etiquetasDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EtiquetasHoteles findById(Long id) {
        return etiquetasDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public EtiquetasHoteles save(EtiquetasHoteles categorias) {
        return etiquetasDao.save(categorias);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        etiquetasDao.deleteById(id);
    }

}
