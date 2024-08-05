package com.dvp.controller;

import com.dvp.model.dto.TicketRequest;
import com.dvp.model.entity.Ticket;
import com.dvp.model.entity.Usuario;
import com.dvp.model.service.ITicketService;
import com.dvp.model.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TicketRestController {

    @Autowired
    ITicketService service;

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/tickets/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> show(@PathVariable String id) {
        Ticket t = null;
        Map<String, Object> response = new HashMap<>();
        try {
            t = service.findById(id);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (t == null) {
            response.put("mensaje", "El Ticket ID ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ticket>(t, HttpStatus.OK);
    }

    @GetMapping("/tickets")
    public List<Ticket> index() {
        return service.findAll();
    }

    @GetMapping("/tickets/page/{page}")
    public Page<Ticket> index(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return service.findAll(pageable);
    }

    @PostMapping("/tickets")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  crear(@RequestBody TicketRequest ticketRequest) {
        Ticket t= null;
        Map<String, Object> response = new HashMap<>();
        try {
            Usuario usuario = usuarioService.findById(ticketRequest.usuarioId());
            Ticket ticket = new Ticket();
            ticket.setEstado(ticketRequest.estado());
            ticket.setUsuario(usuario);
            ticket.setCreacion(ticketRequest.creacion());
            t = service.save(ticket);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Ticket ha sido creado con éxito!");
        response.put("Ticket", t);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<?> update(@RequestBody TicketRequest ticketRequest, @PathVariable String id) {

        Ticket ticketActual = service.findById(id);
        Ticket ticketUpdated = null;
        Map<String, Object> response = new HashMap<>();

        if (ticketActual == null) {
            response.put("mensaje", "Error: No se puede actualizar. El Ticket ID "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            Usuario usuario = usuarioService.findById(ticketRequest.usuarioId());
            ticketActual.setUsuario(usuario);
            ticketActual.setCreacion(ticketRequest.creacion());
            ticketActual.setEstado(ticketRequest.estado());
            ticketUpdated = service.save(ticketActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la actualización");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Ticket ha sido actualizado con éxito!");
        response.put("Ticket", ticketUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            service.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar Ticket!");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Ticket ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
