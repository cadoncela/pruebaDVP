package com.dvp.controller;

import com.dvp.model.entity.Ticket;
import com.dvp.model.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TicketGraphqlController {

    @Autowired
    ITicketService service;

    @QueryMapping
    public List<Ticket> listarTickets(){
        return service.findAll();
    }

    @QueryMapping
    public Ticket listarPorId(@Argument String id){
        return service.findById(id);
    }

    @MutationMapping
    public Ticket guardarTicket(@Argument Ticket ticket){
        System.out.println("Save");
        return service.save(ticket);
    }
}
