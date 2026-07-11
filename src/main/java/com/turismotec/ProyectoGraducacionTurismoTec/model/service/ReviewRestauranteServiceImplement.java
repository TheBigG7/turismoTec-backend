package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IReviewRestauranteDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewRestauranteServiceImplement implements IReviewRestauranteService{

    @Autowired
    public IReviewRestauranteDao reviewRestauranteDao;

    @Override
    @Transactional(readOnly = true)
    public List<ReviewRestaurante> findAll() {
        return reviewRestauranteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewRestaurante findById(Long id) {
        return reviewRestauranteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ReviewRestaurante save(ReviewRestaurante reviewRestaurante) {
        return reviewRestauranteDao.save(reviewRestaurante);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        reviewRestauranteDao.deleteById(id);
    }
}
