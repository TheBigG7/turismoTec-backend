package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewHotel;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;

import java.util.List;

public interface IReviewHotelService {

    public List<ReviewHotel> findAll();

    public ReviewHotel findById(Long id);

    public ReviewHotel save(ReviewHotel reviewHotel);

    public void delete(Long id);
}
