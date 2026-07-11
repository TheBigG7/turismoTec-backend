package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Restaurante;
import java.util.List;

public interface IRestauranteService {

    public List<Restaurante> findAll();

    public Restaurante findById(Long id);

    public Restaurante save(Restaurante restaurante);

    public void delete(Long id);

    public int cantidadDeRestaurantesCreados(Long idUsuario);

    public int cantidadDeRestaurantesCreadosParaAdmin();
}
