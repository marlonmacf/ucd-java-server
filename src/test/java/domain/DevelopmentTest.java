package domain.services;

import domain.Development;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Development.class)
class DevelopmentTest {

    DevelopmentTest() {
        System.setProperty("spring.profiles.active", "development");
    }
}
