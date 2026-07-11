package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IImagenesHotelDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesHoteles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImagenesHotelService {

    @Autowired
    private IImagenesHotelDao iImagenesHotelesDao;

    public List<ImagenesHoteles> findAll(){return iImagenesHotelesDao.findAll();}

    @Transactional
    public void save(ImagenesHoteles imagenesHotel){iImagenesHotelesDao.save(imagenesHotel);}

    public ImagenesHoteles findById(Long id){return iImagenesHotelesDao.findById(id).orElse(null);}

    @Transactional
    public void delete(Long id){iImagenesHotelesDao.deleteById(id);}

    @Transactional
    public void deleteImgById(Long id){iImagenesHotelesDao.deleteByHotelesId(id);}

    @Transactional(readOnly = true)
    public List<ImagenesHoteles> findByHotelId(Long idHotel) {
        return iImagenesHotelesDao.findByHotelesIdHotel(idHotel);
    }
}

