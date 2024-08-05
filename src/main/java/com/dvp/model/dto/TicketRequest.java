package com.dvp.model.dto;

public record TicketRequest(
        String creacion,
        int estado,
        Long usuarioId
) {
}
