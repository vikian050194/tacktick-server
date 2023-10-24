package tacktick.controllers;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import tacktick.models.Game;

public class GameTests extends BaseTest {

    @Test
    public void getAllGames() {
        // Arrange
        Game game = new Game("foo");
        repository.save(game);

        // Act
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<Game[]> response = restTemplate.exchange("/games", HttpMethod.GET, entity, Game[].class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Game[] body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.length).isEqualTo(1);
        assertThat(body[0].name).isEqualTo("foo");
    }

    @Test
    public void createNewGame() {
        // Arrange
        Game game = new Game("foo");

        // Act
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Game> entity = new HttpEntity<>(game, headers);
        ResponseEntity<Game> response = restTemplate.exchange("/games", HttpMethod.POST, entity, Game.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Game body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.name).isEqualTo("foo");

        List<Game> games = repository.findAll();
        assertThat(games.size()).isEqualTo(1);
        assertThat(games.get(0).name).isEqualTo("foo");
    }

    @Test
    public void updateGame() {
        // Arrange
        Game game = new Game("foo");
        Game initialGame = repository.save(game);

        // Act
        Game updatedGame = new Game("bar");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Game> entity = new HttpEntity<>(updatedGame, headers);
        String url = String.format("/games/%s", initialGame.id);
        ResponseEntity<Game> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Game.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        Game body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.name).isEqualTo("bar");

        List<Game> games = repository.findAll();
        assertThat(games.size()).isEqualTo(1);
        assertThat(games.get(0).name).isEqualTo("bar");
    }

    @Test
    public void updateWrongGame() {
        // Act
        Game updatedGame = new Game("bar");

        int wrongGameId = 1;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Game> entity = new HttpEntity<>(updatedGame, headers);
        String url = String.format("/games/%s", wrongGameId);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        // TODO why do we have such response with timestamp and so on?
        assertThat(response.hasBody()).isTrue();
    }

    @Test
    public void deleteGame() {
        // Arrange
        Game game = new Game("foo");
        Game savedGame = repository.save(game);

        // Act
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        String url = String.format("/games/%s", savedGame.id);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isFalse();

        List<Game> games = repository.findAll();
        assertThat(games.size()).isEqualTo(0);
    }

    @Test
    public void deleteWrongGame() {
        // Act
        int wrongGameId = 1;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        String url = String.format("/games/%s", wrongGameId);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.hasBody()).isTrue();
    }
}
