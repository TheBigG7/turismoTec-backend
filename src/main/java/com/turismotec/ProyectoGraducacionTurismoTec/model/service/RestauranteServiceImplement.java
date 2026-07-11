package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IRestauranteDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Hoteles;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteServiceImplement implements IRestauranteService {

    @Autowired
    public IRestauranteDao restauranteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Restaurante> findAll() {
        return restauranteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Restaurante findById(Long id) {
        return restauranteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Restaurante save(Restaurante restaurante) {
        return restauranteDao.save(restaurante);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        restauranteDao.deleteById(id);
    }

    @Override
    public int cantidadDeRestaurantesCreados(Long idUsuario) {
        return restauranteDao.cantidadDeRestaurantesCreados(idUsuario);
    }

    @Override
    public int cantidadDeRestaurantesCreadosParaAdmin() {
        return restauranteDao.cantidadDeRestaurantesCreadosParaAdmin();
    }

}
