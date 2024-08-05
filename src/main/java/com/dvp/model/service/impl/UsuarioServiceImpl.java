package com.dvp.model.service.impl;

import com.dvp.model.dao.IUsuarioDao;
import com.dvp.model.entity.Usuario;
import com.dvp.model.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    IUsuarioDao dao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return dao.findById(id).orElse(null);
    }

}
