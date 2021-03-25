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
public class MetodoAgrupamientoEnumTest {

    @Test
    public void testValues() {
        MetodoAgrupamientoEnum[] expResult = new MetodoAgrupamientoEnum[]{MetodoAgrupamientoEnum.HORIZONTAL, MetodoAgrupamientoEnum.VERTICAL, MetodoAgrupamientoEnum.DIAGONAL_PRINCIPAL_INFERIOR, MetodoAgrupamientoEnum.DIAGONAL_PRINCIPAL_SUPERIOR, MetodoAgrupamientoEnum.DIAGONAL_SECUNDARIA_INFERIOR, MetodoAgrupamientoEnum.DIAGONAL_SECUNDARIA_SUPERIOR};
        MetodoAgrupamientoEnum[] result = MetodoAgrupamientoEnum.values();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testValueOf() {
        String name = "HORIZONTAL";
        MetodoAgrupamientoEnum expResult = MetodoAgrupamientoEnum.HORIZONTAL;
        MetodoAgrupamientoEnum result = MetodoAgrupamientoEnum.valueOf(name);
        assertEquals(expResult, result);
    }

}
