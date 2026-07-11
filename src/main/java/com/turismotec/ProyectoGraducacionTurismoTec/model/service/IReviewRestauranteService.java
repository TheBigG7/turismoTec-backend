package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewRestaurante;

import java.util.List;

public interface IReviewRestauranteService {

    public List<ReviewRestaurante> findAll();

    public ReviewRestaurante findById(Long id);

    public ReviewRestaurante save(ReviewRestaurante reviewRestaurante);

    public void delete(Long id);
}
