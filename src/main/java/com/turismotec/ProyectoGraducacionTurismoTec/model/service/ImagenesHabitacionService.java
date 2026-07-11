package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IImagenesHabitacionesDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesHabitaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImagenesHabitacionService {

    @Autowired
    private IImagenesHabitacionesDao iImagenesHabitacionesDao;

    public List<ImagenesHabitaciones> findAll(){return iImagenesHabitacionesDao.findAll();}

    @Transactional
    public void save(ImagenesHabitaciones imagenesHabitacion){iImagenesHabitacionesDao.save(imagenesHabitacion);}

    public ImagenesHabitaciones findById(Long id){return iImagenesHabitacionesDao.findById(id).orElse(null);}

    @Transactional
    public void delete(Long id){iImagenesHabitacionesDao.deleteById(id);}

    @Transactional
    public void deleteImgById(Long id){iImagenesHabitacionesDao.deleteByHabitacionId(id);}
}

