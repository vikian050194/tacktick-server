package tacktick.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tacktick.App;
import tacktick.repositories.GameRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("h2")
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {

    @Autowired
    protected GameRepository repository;

    @Autowired
    protected TestRestTemplate restTemplate;

    @BeforeEach
    public void initTest() throws Exception {
        repository.deleteAll();
    }
}
