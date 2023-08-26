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
public class ErrorBody {

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    private DevsuTypeException type;
	
	
}
