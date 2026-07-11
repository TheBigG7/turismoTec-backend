package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.ILugaresDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Lugares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LugaresServiceImplement implements  ILugaresService{

    @Autowired
    public ILugaresDao lugaresDao;

    @Override
    @Transactional(readOnly = true)
    public List<Lugares> findAll() {
        return lugaresDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Lugares findById(Long id) {
        return lugaresDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Lugares save(Lugares lugares) {
        return lugaresDao.save(lugares);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        lugaresDao.deleteById(id);
    }

    @Override
    public List<Lugares> obtenerLugaresAprobadosAndVisualizacionTrue() {
        return lugaresDao.findByAprobadoTrueAndVisualizacionTrue();
    }
    @Override
    public List<Lugares> obtenerLugaresCreadoPorAdmin(){
        return lugaresDao.findBycreadoPorTrue();
    }
}
