package com.dvp.model.dao;

import com.dvp.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketDao extends JpaRepository<Ticket, String> {
}
