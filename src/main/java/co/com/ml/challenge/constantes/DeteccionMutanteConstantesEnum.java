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
public enum DeteccionMutanteConstantesEnum {

    TAMANIO_MINIMO_COMPARACION("4"),
    CARACTERES_MATRIZ_VALIDOS("A,T,C,G"),
    SEPARADOR_VALORES(",");

    public final String valor;

    private DeteccionMutanteConstantesEnum(String valor) {
        this.valor = valor;
    }

}
