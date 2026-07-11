package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IReviewHotelDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewHotelServiceImplement implements IReviewHotelService{

    @Autowired
    public IReviewHotelDao reviewHotelDao;

    @Override
    @Transactional(readOnly = true)
    public List<ReviewHotel> findAll() {
        return reviewHotelDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewHotel findById(Long id) {
        return reviewHotelDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ReviewHotel save(ReviewHotel reviewHotel) {
        return reviewHotelDao.save(reviewHotel);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        reviewHotelDao.deleteById(id);
    }
}
