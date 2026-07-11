package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IUsuarioDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService{

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioDao.deleteById(id);
    }

    @Override
    public List<String> CantidadPersonasRegistradasByMesYYear(int year) {
        return  usuarioDao.CantidadPersonasRegistradasByMesYYear(year);
    }

    @Override
    public Long totalUsuarioRegistrados() {
        return usuarioDao.totalUsuarioRegistrados();
    }

    @Override
    public List<String> usuarioPorPaisOrigen() {
        return usuarioDao.usuarioPorPaisOrigen();
    }

    @Override
    public List<Usuario> findByidpersonaqueloCreo(Long idUsuario) {
        return usuarioDao.findByidpersonaqueloCreo(idUsuario);
    }
}
