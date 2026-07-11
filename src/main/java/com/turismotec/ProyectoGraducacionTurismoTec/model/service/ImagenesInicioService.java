package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IImagenesInicioDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesInicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ImagenesInicioService {

    @Autowired
    private IImagenesInicioDao imagenesInicioDao;

    @Transactional(readOnly = true)
    public List<ImagenesInicio> findAll(){
        return imagenesInicioDao.findAll();
    }

    @Transactional
    public void save(String url){
        ImagenesInicio imagenesInicio = new ImagenesInicio();
        imagenesInicio.setUrl(url);
        imagenesInicioDao.save(imagenesInicio);
    }
}
