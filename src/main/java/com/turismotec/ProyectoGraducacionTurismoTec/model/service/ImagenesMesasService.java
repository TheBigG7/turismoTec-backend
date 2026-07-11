package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IImagenesMesaDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesMesas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImagenesMesasService {

    @Autowired
    private IImagenesMesaDao iImagenesMesasDao;

    public List<ImagenesMesas> findAll() {
        return iImagenesMesasDao.findAll();
    }

    @Transactional
    public void save(ImagenesMesas imagenesHabitacion) {
        iImagenesMesasDao.save(imagenesHabitacion);
    }

    public ImagenesMesas findById(Long id) {
        return iImagenesMesasDao.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Long id) {
        iImagenesMesasDao.deleteById(id);
    }

    @Transactional
    public void deleteImgById(Long id) {
        iImagenesMesasDao.deleteByMesasId(id);
    }
}

