package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewRestaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRestauranteDao extends JpaRepository<ReviewRestaurante,Long> {
}
