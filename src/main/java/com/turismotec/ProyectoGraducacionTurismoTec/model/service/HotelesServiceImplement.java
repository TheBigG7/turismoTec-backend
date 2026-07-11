package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IHotelesDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Hoteles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelesServiceImplement implements IHotelesService{

    @Autowired
    public IHotelesDao hotelesDao;

    @Override
    @Transactional(readOnly = true)
    public List<Hoteles> findAll() {
        return hotelesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Hoteles findById(Long id) {
        return hotelesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Hoteles save(Hoteles hoteles) {
        return hotelesDao.save(hoteles);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        hotelesDao.deleteById(id);
    }

    @Override
    public int cantidadDeHotelesCreados(Long id) {
        return hotelesDao.cantidadDeHotelesCreados(id);
    }

    @Override
    public int cantidadDeHotelesCreadosParaAdmin() {
        return hotelesDao.cantidadDeHotelesCreadosParaAdmin();
    }

}
