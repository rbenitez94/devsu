package org.devsu;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.devsu.DTO.CuentaRequest;
import org.devsu.DTO.CuentaResponse;
import org.devsu.controller.CuentaController;
import org.devsu.service.CuentaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CuentaController.class)
@MockBean(CuentaService.class)
@Log4j2
public class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenCuenta_createReturnCuenta() throws Exception {
        CuentaRequest request = new CuentaRequest("4546648", "Ahorro", BigDecimal.valueOf(2000), true, 1);
        CuentaResponse cuentaResponse = new CuentaResponse(1, "4546648", "Ahorro", BigDecimal.valueOf(2000), true);
        when(cuentaService.crear(any(CuentaRequest.class))).thenReturn(cuentaResponse);
        ResultActions response = mockMvc.perform(post("/cuentas/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
        response.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nroCuenta", Matchers.is(request.getNroCuenta())))
                .andExpect(jsonPath("$.tipoCuenta", Matchers.is(request.getTipoCuenta())));


    }

}
