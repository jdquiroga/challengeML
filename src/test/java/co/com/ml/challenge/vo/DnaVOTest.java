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
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;
import pl.pojo.tester.api.assertion.Method;

/**
 *
 * @author usuario
 */
@SpringBootTest
public class DnaVOTest {

    @Test
    public void DnaVOTest() {
        final Class<?> classUnderTest = DnaVO.class;
        assertPojoMethodsFor(classUnderTest).testing(Method.GETTER, Method.SETTER, Method.EQUALS, Method.HASH_CODE, Method.CONSTRUCTOR, Method.TO_STRING).areWellImplemented();
    }

    @Test
    public void DnaVOToStringTest() {
        ToStringVerifier.forClass(DnaVO.class)
                .withClassName(NameStyle.SIMPLE_NAME)
                .verify();
    }

    @Test
    public void DnaVOEqualsTest() {
        EqualsVerifier.forClass(DnaVO.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT)
                .suppress(Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }

}
