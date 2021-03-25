/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ml.challenge.vo;

import com.jparams.verifier.tostring.NameStyle;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import pl.pojo.tester.api.assertion.Method;

/**
 *
 * @author usuario
 */
@SpringBootTest
public class EstadisticasVOTest {

    @Test
    public void EstadisticasVOTest() {
        final Class<?> classUnderTest = EstadisticasVO.class;
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER, Method.EQUALS, Method.HASH_CODE, Method.CONSTRUCTOR, Method.TO_STRING).areWellImplemented();
    }

    @Test
    public void EstadisticasVOToStringTest() {
        ToStringVerifier.forClass(EstadisticasVO.class)
                .withClassName(NameStyle.SIMPLE_NAME)
                .verify();
    }

    @Test
    public void EstadisticasVOEqualsTest() {
        EqualsVerifier.forClass(EstadisticasVO.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }

    @Test
    public void EstadisticasVOEqualsAtributesTest() {
        EstadisticasVO estadisticasVO = new EstadisticasVO(0, 0, Double.NaN);
        EstadisticasVO estadisticasCompareVO = new EstadisticasVO(0, 0, Double.NaN);

        Assert.assertTrue(estadisticasVO.getCount_human_dna().equals(estadisticasCompareVO.getCount_human_dna()));
        Assert.assertTrue(estadisticasVO.getCount_mutant_dna().equals(estadisticasCompareVO.getCount_mutant_dna()));
        Assert.assertTrue(estadisticasVO.getRatio().equals(estadisticasCompareVO.getRatio()));
    }

}
