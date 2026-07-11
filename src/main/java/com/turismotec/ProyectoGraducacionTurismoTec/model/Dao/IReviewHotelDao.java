package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewHotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewHotelDao extends JpaRepository<ReviewHotel,Long> {
}
