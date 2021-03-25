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
public class DeteccionMutanteConstantesEnumTest {

    @Test
    public void testValues() {
        DeteccionMutanteConstantesEnum[] expResult = new DeteccionMutanteConstantesEnum[]{DeteccionMutanteConstantesEnum.TAMANIO_MINIMO_COMPARACION, DeteccionMutanteConstantesEnum.CARACTERES_MATRIZ_VALIDOS, DeteccionMutanteConstantesEnum.SEPARADOR_VALORES};
        DeteccionMutanteConstantesEnum[] result = DeteccionMutanteConstantesEnum.values();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testValueOf() {
        String name = "TAMANIO_MINIMO_COMPARACION";
        DeteccionMutanteConstantesEnum expResult = DeteccionMutanteConstantesEnum.TAMANIO_MINIMO_COMPARACION;
        DeteccionMutanteConstantesEnum result = DeteccionMutanteConstantesEnum.valueOf(name);
        assertEquals(expResult, result);
    }

}
