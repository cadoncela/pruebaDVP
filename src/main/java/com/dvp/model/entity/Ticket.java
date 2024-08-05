package com.dvp.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ticket {

    @Id
    private String id;
    private String creacion;
    private String actualizacion;
    private int estado;

    @JsonIgnoreProperties(value={"tickets", "hibernateLazyInitializer", "handler"}, allowSetters =true )
    @ManyToOne
    private Usuario usuario;
}
