package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ReviewPlaces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReviewPlacesDao extends JpaRepository<ReviewPlaces,Long> {

}
