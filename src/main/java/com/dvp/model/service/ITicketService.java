package com.dvp.model.service;

import com.dvp.model.entity.Ticket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ITicketService {

    public List<Ticket> findAll();

    public Page<Ticket> findAll(Pageable page);

    public Ticket findById(String id);

    public Ticket save(Ticket ticket);

    public void delete(String id);

}
