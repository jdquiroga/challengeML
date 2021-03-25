package co.com.ml.challenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ChallengeApplicationTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void applicationContextLoaded() {
    }

    @Test
    public void applicationContextTest() {
        ChallengeApplication.main(new String[]{});
    }

}
