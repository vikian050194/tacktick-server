package tacktick.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class AppTests extends BaseTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void homeResponse() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).isEqualTo("Spring is here!");
    }
}
