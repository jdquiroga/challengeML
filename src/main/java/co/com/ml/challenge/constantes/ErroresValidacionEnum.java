/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.constantes;

/**
 *
 * @author usuario
 */
public enum ErroresValidacionEnum {

    MENSAJE_TAMANIO_MATRIZ_INVALIDO("El tamaño de la matriz no es suficiente para realizar la validación. Debe ser minimo una matriz de 4x4"),
    MENSAJE_NO_MATRIZ_CUADRADA("La matriz no es cuadrada, se encontro una matriz de tamaño %dx%d"),
    MENSAJE_CARACTER_INVALIDO("La letra %s no se encuentra en los caracteres de validación validos [A,T,C,G]");

    public final String mensaje;

    private ErroresValidacionEnum(String mensaje) {
        this.mensaje = mensaje;
    }
}
