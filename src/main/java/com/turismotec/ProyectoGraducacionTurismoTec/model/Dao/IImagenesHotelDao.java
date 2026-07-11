package com.turismotec.ProyectoGraducacionTurismoTec.model.Dao;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.ImagenesHoteles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImagenesHotelDao extends JpaRepository<ImagenesHoteles, Long> {
    @Modifying
    @Query("DELETE FROM ImagenesHoteles il WHERE il.hoteles.idHotel = :idHotel")
    void deleteByHotelesId(@Param("idHotel") Long idHotel);

    List<ImagenesHoteles> findByHotelesIdHotel(Long hotelId);
}
