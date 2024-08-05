package com.dvp.model.dao;

import com.dvp.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
}
