package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewPlaces;

import java.util.List;

public interface IReviewPlacesService {

    public List<ReviewPlaces> findAll();

    public ReviewPlaces findById(Long id);

    public ReviewPlaces save(ReviewPlaces reviewPlaces);

    public void delete(Long id);

}
