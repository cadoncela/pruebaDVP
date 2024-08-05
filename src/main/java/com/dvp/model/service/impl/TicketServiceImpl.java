package com.dvp.model.service.impl;

import com.dvp.model.dao.ITicketDao;
import com.dvp.model.entity.Ticket;
import com.dvp.model.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    ITicketDao ticketDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ticket> findAll(Pageable page) {
        return ticketDao.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Ticket findById(String id) {
        return ticketDao.findById(id).orElse(null);
    }

    @Override
    //@Transactional
    public Ticket save(Ticket ticket) {
        if (ticket.getId() == null || ticket.getId().isBlank())
            ticket.setId(UUID.randomUUID().toString());
        return ticketDao.save(ticket);
    }

    @Override
    @Transactional
    public void delete(String id) {
        ticketDao.deleteById(id);
    }
}
