/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.dao;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import pl.pojo.tester.api.assertion.Method;

/**
 *
 * @author usuario
 */
public class EstadisticasValidacionTest {

    @Test
    public void EstadisticasValidacionTest() {
        final Class<?> classUnderTest = EstadisticasValidacion.class;
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER, Method.EQUALS, Method.HASH_CODE, Method.CONSTRUCTOR, Method.TO_STRING).areWellImplemented();
    }

    @Test
    public void EstadisticasValidacionToStringTest() {
        ToStringVerifier.forClass(EstadisticasValidacion.class)
                .withClassName(NameStyle.SIMPLE_NAME)
                .verify();
    }

    @Test
    public void EstadisticasValidacionEqualsTest() {
        EqualsVerifier.forClass(EstadisticasValidacion.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }

}
