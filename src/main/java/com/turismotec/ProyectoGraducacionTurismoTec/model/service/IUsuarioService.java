package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Usuario;

import java.util.List;

public interface IUsuarioService{

    public List<Usuario> findAll();

    public Usuario findById(Long id);

    public Usuario save(Usuario usuario);

    public void delete(Long id);

    public List<String> CantidadPersonasRegistradasByMesYYear(int year);

    public Long totalUsuarioRegistrados();

    public List<String> usuarioPorPaisOrigen();

    public List<Usuario> findByidpersonaqueloCreo(Long idUsuario);
}
