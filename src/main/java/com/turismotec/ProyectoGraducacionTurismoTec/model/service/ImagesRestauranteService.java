package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IImagenesRestaurantesDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesRestaurantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ImagesRestauranteService {

    @Autowired
    private IImagenesRestaurantesDao iImagenesRestaurantesDao;

    public List<ImagenesRestaurantes> findAll(){
        return iImagenesRestaurantesDao.findAll();
    }

    @Transactional
    public void save(ImagenesRestaurantes imagenesRestaurante){
        iImagenesRestaurantesDao.save(imagenesRestaurante);
    }

    public ImagenesRestaurantes findById(Long id){
        return iImagenesRestaurantesDao.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Long id){
        iImagenesRestaurantesDao.deleteById(id);
    }

    @Transactional
    public void deleteImgById(Long id){
        iImagenesRestaurantesDao.deleteByRestauranteId(id);
    }
}

