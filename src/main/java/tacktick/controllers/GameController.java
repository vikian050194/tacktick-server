package tacktick.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacktick.exceptions.NotFoundException;
import tacktick.models.Game;
import tacktick.repositories.GameRepository;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository repository;

    @GetMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public Iterable<Game> getGames() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Game getGame(
            @PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.OK)
    public Game addGame(@Valid @RequestBody Game game) {
        return repository.save(game);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Game updateGame(
            @PathVariable Long id,
            @Valid @RequestBody Game inputGame) {
        Game game = repository.findById(id)
                .orElseThrow(NotFoundException::new);

        game.name = inputGame.name;
        return repository.save(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteGame(
            @PathVariable Long id) {
        Game game = repository.findById(id)
                .orElseThrow(NotFoundException::new);

        repository.delete(game);
    }
}
