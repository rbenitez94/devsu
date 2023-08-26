package org.devsu.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String nombre;

    private Integer edad;

    private String genero;

    @Column(length = 20)
    private String identificacion;

    @Column(length = 50)
    private String direccion;

    @Column(length = 20)
    private String telefono;

}
