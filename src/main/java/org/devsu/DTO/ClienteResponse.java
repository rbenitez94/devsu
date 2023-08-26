package org.devsu.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ClienteResponse {

    private long id;

    private String nombre;

    private Integer edad;

    private String genero;

    private String identificacion;

    private String direccion;

    private String telefono;

    private long contrasena;

    private Boolean estado;

}
