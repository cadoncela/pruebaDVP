package com.dvp.model.service;

import com.dvp.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> findAll();

    public Usuario findById(Long id);

}
