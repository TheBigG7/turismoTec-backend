package com.turismotec.ProyectoGraducacionTurismoTec.model.service;

import com.turismotec.ProyectoGraducacionTurismoTec.model.Dao.IAuth_RolDao;
import com.turismotec.ProyectoGraducacionTurismoTec.model.entity.Auth_rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Auth_RolServiceImplement {

    @Autowired
    private IAuth_RolDao iAuthRolDao;

    @Transactional(readOnly = true)
    public List<Auth_rol> findAll(){
        return  iAuthRolDao.findAll();
    }

    @Transactional(readOnly = true)
    public Auth_rol findById(Long id){
        return  iAuthRolDao.findById(id).orElse(null);
    }

    public Auth_rol save(Auth_rol authRol){
        return  iAuthRolDao.save(authRol);
    }

    public void delete(Long id){
        iAuthRolDao.deleteById(id);
    }

    public boolean existsByAuthAndRol(Long idAuth, Long idRol){
        return iAuthRolDao.existsByAuthAndRol(idAuth,idRol);
    }

}
