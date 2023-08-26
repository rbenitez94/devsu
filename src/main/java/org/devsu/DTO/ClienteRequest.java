package org.devsu.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ClienteRequest {

    @NotBlank
    private String nombre;

    private Integer edad;

    private String genero;

    private String identificacion;

    private String direccion;

    @NotBlank
    private String telefono;

    private long contrasena;

    @NotNull
    private Boolean estado;

}
