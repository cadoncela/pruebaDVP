package com.dvp.controller;

import com.dvp.model.dto.TicketRequest;
import com.dvp.model.entity.Ticket;
import com.dvp.model.entity.Usuario;
import com.dvp.model.service.ITicketService;
import com.dvp.model.service.IUsuarioService;
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

    @Autowired
    IUsuarioService usuarioService;

    @QueryMapping
    public List<Ticket> listarTickets(){
        return service.findAll();
    }

    @QueryMapping
    public Ticket listarPorId(@Argument String id){
        return service.findById(id);
    }

    @MutationMapping
    public Ticket guardarTicket(@Argument TicketRequest ticketRequest){
        Usuario usuario = usuarioService.findById(ticketRequest.usuarioId());
        Ticket ticket = new Ticket();
        ticket.setEstado(ticketRequest.estado());
        ticket.setUsuario(usuario);
        ticket.setCreacion(ticketRequest.creacion());
        return service.save(ticket);
    }
}
