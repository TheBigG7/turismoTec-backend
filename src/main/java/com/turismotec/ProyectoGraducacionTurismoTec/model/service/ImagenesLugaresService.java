package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IImagenesLugaresDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesLugar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImagenesLugaresService {

    @Autowired
    private IImagenesLugaresDao iImagenesLugaresDao;

    public List<ImagenesLugar> findAll(){
        return iImagenesLugaresDao.findAll();
    }

    @Transactional
    public void save(ImagenesLugar imagenesLugar){
        iImagenesLugaresDao.save(imagenesLugar);
    }

    public ImagenesLugar findById(Long id){
        return iImagenesLugaresDao.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Long id){
        iImagenesLugaresDao.deleteById(id);
    }

    public void deleteImgById(Long id){
        iImagenesLugaresDao.deleteByLugaresId(id);
    }
}
