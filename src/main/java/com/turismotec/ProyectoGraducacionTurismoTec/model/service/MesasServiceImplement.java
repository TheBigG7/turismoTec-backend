package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IMesasDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Mesas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MesasServiceImplement implements IMesasService {

    @Autowired
    public IMesasDao mesasDao;

    @Override
    @Transactional(readOnly = true)
    public List<Mesas> findAll() {
        return mesasDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mesas findById(Long id) {
        return mesasDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Mesas save(Mesas mesas) {
        return mesasDao.save(mesas);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        mesasDao.deleteById(id);
    }
}
