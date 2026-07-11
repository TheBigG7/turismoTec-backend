package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IReviewPlacesDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewPlaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewPlacesServiceImplement implements IReviewPlacesService {

    @Autowired
    public IReviewPlacesDao reviewPlacesDao;

    @Override
    @Transactional(readOnly = true)
    public List<ReviewPlaces> findAll() {
        return reviewPlacesDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewPlaces findById(Long id) {
        return reviewPlacesDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ReviewPlaces save(ReviewPlaces reviewPlaces) {
        return reviewPlacesDao.save(reviewPlaces);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        reviewPlacesDao.deleteById(id);
    }

}
