/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.business.exception;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

/**
 *
 * @author usuario
 */
@SpringBootTest
public class DeteccionMutantesExceptionTest {

    @Test
    public void testDeteccionMutantesExceptionConstructorStatusMessageThrowable() {
        DeteccionMutantesException exception = assertThrows(DeteccionMutantesException.class, () -> {
            try {
                Integer.parseInt("1a");
            } catch (NumberFormatException ex) {
                throw new DeteccionMutantesException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
            }
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDeteccionMutantesExceptionConstructorStatusMessage() {
        DeteccionMutantesException exception = assertThrows(DeteccionMutantesException.class, () -> {
            try {
                Integer.parseInt("1a");
            } catch (NumberFormatException ex) {
                throw new DeteccionMutantesException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
            }
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testDeteccionMutantesExceptionConstructorStatus() {
        DeteccionMutantesException exception = assertThrows(DeteccionMutantesException.class, () -> {
            try {
                Integer.parseInt("1a");
            } catch (NumberFormatException ex) {
                throw new DeteccionMutantesException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        });

        String expectedMessage = "500";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
