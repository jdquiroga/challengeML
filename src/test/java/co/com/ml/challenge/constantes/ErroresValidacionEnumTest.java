/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.constantes;

import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author usuario
 */
@SpringBootTest
public class ErroresValidacionEnumTest {

    @Test
    public void testValues() {
        ErroresValidacionEnum[] expResult = new ErroresValidacionEnum[]{ErroresValidacionEnum.MENSAJE_TAMANIO_MATRIZ_INVALIDO, ErroresValidacionEnum.MENSAJE_NO_MATRIZ_CUADRADA, ErroresValidacionEnum.MENSAJE_CARACTER_INVALIDO};
        ErroresValidacionEnum[] result = ErroresValidacionEnum.values();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testValueOf() {
        String name = "MENSAJE_TAMANIO_MATRIZ_INVALIDO";
        ErroresValidacionEnum expResult = ErroresValidacionEnum.MENSAJE_TAMANIO_MATRIZ_INVALIDO;
        ErroresValidacionEnum result = ErroresValidacionEnum.valueOf(name);
        assertEquals(expResult, result);
    }

}
