/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author usuario
 */
public class DeteccionMutantesException extends ResponseStatusException {

    public DeteccionMutantesException(HttpStatus status) {
        super(status);
    }

    public DeteccionMutantesException(HttpStatus hs, String string) {
        super(hs, string);
    }

    public DeteccionMutantesException(HttpStatus hs, String string, Throwable thrwbl) {
        super(hs, string, thrwbl);
    }

}
