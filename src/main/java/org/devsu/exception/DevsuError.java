package org.devsu.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class DevsuError extends Exception {

    private String errorMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;

    private DevsuTypeException type;
	
	
}
